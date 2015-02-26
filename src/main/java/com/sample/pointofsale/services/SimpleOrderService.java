package com.sample.pointofsale.services;

import java.math.BigDecimal;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.sample.pointofsale.UnknownPriceException;
import com.sample.pointofsale.model.LineItem;
import com.sample.pointofsale.model.Order;
import com.sample.pointofsale.model.Price;

/**
 * A service for managing orders and calculating order totals
 * @note It could/should be extended to save/load orders from storage
 * @author Lucas Anderson
 */
public class SimpleOrderService implements OrderService {

    /**
     * The product service for looking up product information (prices)
     */
    ProductService productService;

    public SimpleOrderService(ProductService productService) {
        this.productService = productService;
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.OrderService#newOrder()
     */
    @Override
    public Order newOrder() {
        return new Order("0");
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.services.OrderService#cancelOrder(com.sample.pointofsale.model.Order)
     */
    @Override
    public void cancelOrder(Order order) {
        // This currently does nothing since orders are not loaded / stored
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.OrderService#calculateOrder(com.sample.pointofsale.Order)
     */
    @Override
    public Price calculateOrder(Order order) throws UnknownPriceException {
        BigDecimal orderTotal = new BigDecimal("0.00");
        // Loop through each item in the order
        for (LineItem item : order.getItems()) {
            BigDecimal itemSubTotal = new BigDecimal("0.00");
            int itemQuantity = item.getQuantity();
            NavigableMap<Integer, Price> prices = new TreeMap<>(productService.getPrices(item.getProductCode(),itemQuantity).toSortedMap());
            // While we still have an unaccounted for item quantity try and find a price that satisfies it
            while (itemQuantity > 0) {
                Entry<Integer, Price> bestFit = prices.floorEntry(itemQuantity);
                if (bestFit != null) {
                    // Found the best fitting quantity (closest to the requested quantity without going over)
                    itemSubTotal = itemSubTotal.add(bestFit.getValue().getAmount());
                    itemQuantity -= bestFit.getKey();
                } else {
                    // Can't find an appropriate price for the remaining quantity (uh oh!)
                    throw new UnknownPriceException("Unknown price for: " + item.getProductCode() + " quantity: " + itemQuantity);
                }
            }
            item.getSubTotal().setAmount(itemSubTotal);
            // Sure hope all the prices were in the same currency
            orderTotal = orderTotal.add(item.getSubTotal().getAmount());
        }
        order.getTotal().setAmount(orderTotal);
        return order.getTotal();
    }
}
