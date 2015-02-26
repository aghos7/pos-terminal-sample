package com.sample.pointofsale;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.sample.kisspointofsale.KissPointOfSaleTerminal;
import com.sample.pointofsale.services.OrderService;
import com.sample.pointofsale.services.ProductService;
import com.sample.pointofsale.services.SimpleOrderService;
import com.sample.pointofsale.services.SimpleProductService;

/**
 * A Parameterized test to test some data sets
 * on multiple implementations of PointOfSaleTerminal
 * @author Lucas Anderson 
 */
@RunWith(Parameterized.class)
public class TestParameterizedPointOfSaleTerminal {
    
    private PointOfSaleTerminal pos;
    private List<String> cart;
    private Map<String, Map<Integer, String>> products;
    private String expectedTotal;
    
    public TestParameterizedPointOfSaleTerminal(PointOfSaleTerminal posImpl, List<String> cart, 
            Map<String, Map<Integer, String>> products, String expectedTotal) {
        this.pos = posImpl;
        this.cart = cart;
        this.products = products;
        this.expectedTotal = expectedTotal;
    }
    
    @Parameters(name= "{index}: {0}, {1}, {2}, {3}")
    public static Collection<Object[]> getParameters()
    {   
        Map<String, Map<Integer, String>> products1 = new HashMap<>();
        {
            Map<Integer, String> aPrices = new HashMap<>();
            {
                aPrices.put(1, "1.25");
                aPrices.put(3, "3.00");
            }
            Map<Integer, String> bPrices = new HashMap<>();
            {
                bPrices.put(1, "4.25");
            }
            Map<Integer, String> cPrices = new HashMap<>();
            {
                cPrices.put(1, "1.00");
                cPrices.put(6, "5.00");
            }
            Map<Integer, String> dPrices = new HashMap<>();
            {
                dPrices.put(1, "0.75");
            }
            Map<Integer, String> ePrices = new HashMap<>();
            {
                ePrices.put(2, "3.00");
            }
            products1.put("A", aPrices);
            products1.put("B", bPrices);
            products1.put("C", cPrices);
            products1.put("D", dPrices);
        }
        
        List<String> cart1 = Arrays.asList("A","B","C","D","A","B","A");
        List<String> cart2 = Arrays.asList("C","C","C","C","C","C","C");
        List<String> cart3 = Arrays.asList("A","B","C","D");
        
        ProductService productService = new SimpleProductService();
        OrderService orderService = new SimpleOrderService(productService);
        PointOfSaleTerminal simplePos = new SimplePointOfSaleTerminal(productService, orderService);
        
        return Arrays.asList(new Object[][] {
                // Scan these items in this order: ABCDABA; Verify the total price is $13.25.
                { new KissPointOfSaleTerminal(), cart1, products1, "13.25" },
                // Scan these items in this order: CCCCCCC; Verify the total price is $6.00.
                { new KissPointOfSaleTerminal(), cart2, products1, "6.00" },
                //Scan these items in this order: ABCD; Verify the total price is $7.25.
                { new KissPointOfSaleTerminal(), cart3, products1, "7.25" },
                // Scan these items in this order: ABCDABA; Verify the total price is $13.25.
                { simplePos, cart1, products1, "13.25" },
                // Scan these items in this order: CCCCCCC; Verify the total price is $6.00.
                { simplePos, cart2, products1, "6.00" },
                //Scan these items in this order: ABCD; Verify the total price is $7.25.
                { simplePos, cart3, products1, "7.25" }
                });
    }
    
    @Test
    public void testPointOfSaleTerminal() {
        try {
            pos.cancelOrder();
            for(Entry<String, Map<Integer, String>> product : products.entrySet()) {
                for(Entry<Integer, String> prices : product.getValue().entrySet()) {
                    pos.setPricing(product.getKey(), prices.getKey(), prices.getValue());
                }
            }
            
            for(String item : cart) {
                pos.scan(item);
            }
            
            assertThat(pos.calculateTotal().toString(), equalTo(expectedTotal));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    

}
