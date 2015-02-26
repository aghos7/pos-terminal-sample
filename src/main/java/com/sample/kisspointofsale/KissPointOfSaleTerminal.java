package com.sample.kisspointofsale;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.sample.pointofsale.InvalidQuantityException;
import com.sample.pointofsale.PointOfSaleTerminal;
import com.sample.pointofsale.UnknownPriceException;
import com.sample.pointofsale.UnknownProductException;

/**
 * A very simple way to solve this problem
 * Not very flexible
 * @author Lucas Anderson
 */
public class KissPointOfSaleTerminal implements PointOfSaleTerminal {

    private Map<String, NavigableMap<Integer, BigDecimal>> prices;

    private Map<String, Integer> order;

    /**
     * Default constructor for a [K]eep [I]t [S]imple [S]tupid Point of sale terminal
     */
    public KissPointOfSaleTerminal() {
        prices = new HashMap<>();
        order = new LinkedHashMap<>();
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
        if (prices.containsKey(productCode)) {
            order.put(productCode, (order.containsKey(productCode) ? order.get(productCode) + quantity : quantity));
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
        BigDecimal total = new BigDecimal("0.00");
        for (Entry<String, Integer> item : order.entrySet()) {
            int itemQuantity = item.getValue();
            while (itemQuantity > 0) {
                Entry<Integer, BigDecimal> bestPrice = prices.get(item.getKey()).floorEntry(itemQuantity);
                if (bestPrice == null) {
                    throw new UnknownPriceException("Unknown price for: " + item.getKey() + " quantity: " + itemQuantity);
                } else {
                    total = total.add(bestPrice.getValue());
                    itemQuantity -= bestPrice.getKey();
                }
            }
        }
        return total;
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#setPricing(java.lang.String, int, java.lang.String)
     */
    @Override
    public void setPricing(String productCode, int numUnits, String price) {
        NavigableMap<Integer, BigDecimal> currentPrices;
        if (prices.containsKey(productCode)) {
            currentPrices = prices.get(productCode);
        } else {
            currentPrices = new TreeMap<>();
            prices.put(productCode, currentPrices);
        }
        currentPrices.put(numUnits, new BigDecimal(price));
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.PointOfSaleTerminal#cancelOrder()
     */
    @Override
    public void cancelOrder() {
        order.clear();
    }
}
