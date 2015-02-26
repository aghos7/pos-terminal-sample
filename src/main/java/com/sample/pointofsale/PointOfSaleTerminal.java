package com.sample.pointofsale;

import java.math.BigDecimal;

/**
 * A simple interface for a point of sale terminal
 * @author Lucas Anderson
 */
public interface PointOfSaleTerminal {
    /**
     * Scan a product for the order
     * @param productCode The product code scanned
     * @throws UnknownProductException Unknown product scanned
     * @throws InvalidQuantityException Invalid quantity scanned
     */
    void scan(String productCode) throws UnknownProductException, InvalidQuantityException;

    /**
     * Scan a specific quantity of a product for the order
     * @param productCode The product code scanned
     * @param quantity The quantity to scan
     * @throws UnknownProductException Unknown product scanned
     * @throws InvalidQuantityException Invalid quantity scanned
     */
    void scan(String productCode, int quantity) throws UnknownProductException, InvalidQuantityException;

    /**
     * Calculate the total of a sale
     * @return The total cost of a sale
     * @throws UnknownPriceException No price per quantity is known
     */
    BigDecimal calculateTotal() throws UnknownPriceException;

    /**
     * Cancels the order
     */
    void cancelOrder();

    /**
     * Set the price of a product
     * @param productCode The product code of the product
     * @param numUnits The number units per price
     * @param price The price for the specified number of units
     */
    void setPricing(String productCode, int numUnits, String price);
}
