package com.sample.pointofsale;

import java.math.BigDecimal;

import com.sample.pointofsale.model.Order;
import com.sample.pointofsale.model.Price;
import com.sample.pointofsale.model.Product;
import com.sample.pointofsale.services.OrderService;
import com.sample.pointofsale.services.ProductService;

/**
 * A simple implementation of a point of sale terminal
 * It uses a product service for looking up products and prices
 * It uses an order service for maintaining orders and calculating totals
 * I don't think "Service" accurately describes these classes, but I went with the naming convention anyway
 * @author Lucas Anderson
 */
public class SimplePointOfSaleTerminal implements PointOfSaleTerminal {

    /**
     * The current order
     */
    private Order currentOrder;
    
    /**
     * The product service to manage products and prices
     */
    private ProductService productService;
    
    /**
     * The order to service to manage orders and calculate totals
     */
    private OrderService orderService;

    /**
     * Creates a simple point of sale terminal for scanning items
     * @param productService Service to manage product data
     * @param orderService Service to manage order data
     */
    SimplePointOfSaleTerminal(ProductService productService, OrderService orderService) {
        this.productService = productService;
        this.orderService = orderService;
        currentOrder = this.orderService.newOrder();
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#scan(java.lang.String)
     */
    @Override
    public void scan(String productCode) throws UnknownProductException, InvalidQuantityException {
        scan(productCode, 1);
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#scan(java.lang.String, int)
     */
    @Override
    public void scan(String productCode, int quantity) throws UnknownProductException, InvalidQuantityException {
        if(quantity == 0) {
            throw new InvalidQuantityException("Invalid quantity: " + quantity + " for product code: " + productCode);
        }
        
        if (productService.productExists(productCode)) {
            if (quantity > 0) {
                currentOrder.add(productCode, quantity);
            } else {
                currentOrder.remove(productCode, -quantity);
            }
        } else {
            throw new UnknownProductException("Unknown product code: " + productCode);
        }
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#calculateTotal()
     */
    @Override
    public BigDecimal calculateTotal() throws UnknownPriceException {
        return orderService.calculateOrder(currentOrder).getAmount();
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#setPricing(java .lang.String, int, java.lang.String)
     */
    @Override
    public void setPricing(String productCode, int numUnits, String price) {
        if (!productService.productExists(productCode)) {
            // This is a kludge so that I don't need to add the product before setting the price
            // I could have added the ability to add/remove products to the API
            // Since this problem is mostly just concerned with price, the
            // existence of a "Product" is arguably unnecessary, just a set of prices
            // associated with the product
            productService.addProduct(new Product(productCode));
        }
        productService.setPrice(productCode, numUnits, new Price(new BigDecimal(price)));
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#cancelOrder()
     */
    @Override
    public void cancelOrder() {
        orderService.cancelOrder(currentOrder);
        currentOrder = orderService.newOrder();
    }
}
