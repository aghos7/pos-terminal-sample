package com.sample.pointofsale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import com.sample.pointofsale.services.OrderService;
import com.sample.pointofsale.services.ProductService;
import com.sample.pointofsale.services.SimpleOrderService;
import com.sample.pointofsale.services.SimpleProductService;

/**
 * @author Lucas Anderson 
 *
 */
public class TestMiscSimplePointOfSaleTerminal {

    /**
     * Checks that an exception is thrown when an unknown product is scanned
     * @throws Exception
     */
    @Test(expected = UnknownProductException.class)
    public void testUnknownProduct() throws UnknownProductException {
        ProductService productService = new SimpleProductService();
        OrderService orderService = new SimpleOrderService(productService);

        PointOfSaleTerminal terminal = new SimplePointOfSaleTerminal(productService, orderService);
        terminal.setPricing("A", 1, "1.25");

        terminal.cancelOrder();
        try {
            terminal.scan("A");
            terminal.scan("B");
        } catch (InvalidQuantityException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks that an exception is thrown when an unknown price per quantity is found
     * @throws Exception
     */
    @Test(expected = UnknownPriceException.class)
    public void testUnknownPrice() throws UnknownPriceException {
        ProductService productService = new SimpleProductService();
        OrderService orderService = new SimpleOrderService(productService);

        PointOfSaleTerminal terminal = new SimplePointOfSaleTerminal(productService, orderService);
        terminal.setPricing("A", 3, "1.25");

        terminal.cancelOrder();
        try {
            terminal.scan("A");
            terminal.calculateTotal();
        } catch (InvalidQuantityException | UnknownProductException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks that an exception is thrown when an invalid quantity is scanned
     * @throws Exception
     */
    @Test(expected = InvalidQuantityException.class)
    public void testInvalidQuantity() throws InvalidQuantityException {
        ProductService productService = new SimpleProductService();
        OrderService orderService = new SimpleOrderService(productService);

        PointOfSaleTerminal terminal = new SimplePointOfSaleTerminal(productService, orderService);
        terminal.setPricing("A", 3, "1.25");

        terminal.cancelOrder();
        try {
            terminal.scan("A", 0);
        } catch (UnknownProductException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies that removing a scanned quantity works
     * @throws Exception
     */
    @Test
    public void testRemoveScan() {
        ProductService productService = new SimpleProductService();
        OrderService orderService = new SimpleOrderService(productService);

        PointOfSaleTerminal terminal = new SimplePointOfSaleTerminal(productService, orderService);
        terminal.setPricing("A", 1, "1.25");
        terminal.setPricing("A", 3, "3.00");

        terminal.cancelOrder();
        try {
            terminal.scan("A", 3);
            terminal.scan("A", -1);
            assertThat(terminal.calculateTotal().toString(), equalTo("2.50"));
            
            terminal.scan("A", -2);
            assertThat(terminal.calculateTotal().toString(), equalTo("0.00"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
