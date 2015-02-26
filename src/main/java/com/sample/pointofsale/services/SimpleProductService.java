package com.sample.pointofsale.services;

import java.util.Map;
import java.util.HashMap;

import com.sample.pointofsale.model.Price;
import com.sample.pointofsale.model.Prices;
import com.sample.pointofsale.model.Product;

/**
 * A simple service for managing products and their prices
 * @note Currently it doesn't really manage products, mostly just their prices
 *       It could have arguably just been used to manage prices (i.e. "PricingService")
 * @author Lucas Anderson
 */
public class SimpleProductService implements ProductService {

    /**
     * A place to store the products, could be a DB/Flatfile/etc
     */
    Map<String, Product> productStorage;
    
    /**
     * A place to store a mapping from product code to prices, could be a DB/Flatfile/etc
     */
    Map<String, Prices> pricesStorage;

    /**
     * Default constructor
     */
    public SimpleProductService() {
        productStorage = new HashMap<>();
        pricesStorage = new HashMap<>();
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.services.ProductService#addProduct(com.sample.pointofsale.model.Product)
     */
    @Override
    public void addProduct(Product product) {
        productStorage.put(product.getCode(), product);
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.ProductService#productExists(java.lang.String)
     */
    @Override
    public boolean productExists(String productCode) {
        return productStorage.containsKey(productCode);
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.ProductService#setPrice(java.lang.String, int, com.sample.pointofsale.Price)
     */
    @Override
    public void setPrice(String productCode, int quantity, Price price) {
        Prices prices = (pricesStorage.containsKey(productCode) ? pricesStorage.get(productCode) : new Prices());
        prices.add(quantity, price);
        pricesStorage.put(productCode, prices);
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.ProductService#getPrices(java.lang.String)
     */
    @Override
    public Prices getPrices(String productCode) {
        return pricesStorage.get(productCode);
    }

    /*
     * (non-Javadoc)
     * @see com.sample.pointofsale.ProductService#getPrices(java.lang.String, int)
     */
    @Override
    public Prices getPrices(String productCode, int maxQuantity) {
        Prices result = null;
        if (pricesStorage.containsKey(productCode)) {
            result = new Prices(getPrices(productCode).toSortedMap().headMap(maxQuantity + 1));
        }
        return result;
    }
}
