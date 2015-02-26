package com.sample.pointofsale.services;

import com.sample.pointofsale.model.Price;
import com.sample.pointofsale.model.Prices;
import com.sample.pointofsale.model.Product;

/**
 * A simple interface for managing products and their prices
 * @note I could have create another "setPrice" method that would take a product code and a Map of quantities to prices
 * @author Lucas Anderson
 */
public interface ProductService {
    /**
     * Add a product
     * @param product The product to add
     */
    public void addProduct(Product product);

    /**
     * Determine if a product with the specified product exists
     * @param productCode The product code of the product
     * @return True if the product exists, false otherwise
     */
    boolean productExists(String productCode);

    /**
     * Set the price for a quantity of a product
     * @param productCode The product code of the product
     * @param quantity The quantity per price
     * @param price The price per quantity
     */
    void setPrice(String productCode, int quantity, Price price);

    /**
     * Get all available prices of a product
     * @param productCode The product code of the product
     * @return The available prices
     */
    Prices getPrices(String productCode);

    /**
     * Get the prices per quantity of a product including only quantities less
     * than or equal to the maximum
     * @param productCode The product code of the product
     * @param maxQuantity The maximum number of units
     * @return The available prices for quantities less than or equal to maxQuantity
     */
    Prices getPrices(String productCode, int maxQuantity);
}
