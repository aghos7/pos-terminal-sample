package com.sample.pointofsale.model;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A collection of prices per quantity
 * @note This could have just used a navigable map to store the data rather than a sorted map, but Navigable map is not
 *       currently supported by JPA or Hibernate; SortedMap is
 * @author Lucas Anderson
 */
public class Prices {
    /**
     * Map a quantity to a specified price
     * I.E. 1 => 3.00, 5 => 10.00
     */
    private SortedMap<Integer, Price> prices;

    /**
     * Construct a collection of prices per quantity
     */
    public Prices() {
        this.prices = new TreeMap<>();
    }

    /**
     * Construct a collection of prices per quantity
     * @param prices A mapping of quantity to price
     */
    public Prices(Map<Integer, Price> prices) {
        this.prices = new TreeMap<>(prices);
    }

    /**
     * Add a price for a given quantity
     * @param quantity The quantity to add
     * @param price The price of the quantity
     */
    public void add(int quantity, Price price) {
        prices.put(quantity, price);
    }

    /**
     * Remove a quantity
     * @param quantity The quantity to remove
     * @return The price of the quantity removed
     */
    public Price remove(int quantity) {
        return prices.remove(quantity);
    }

    /**
     * Get the price for a quantity
     * @param quantity The quantity
     * @return The price for the specified quantity
     */
    public Price getPrice(int quantity) {
        return prices.get(quantity);
    }

    /**
     * Get a sorted map of quantities to prices
     * @return A map sorted naturally by quantity
     */
    public SortedMap<Integer, Price> toSortedMap() {
        return prices;
    }
}
