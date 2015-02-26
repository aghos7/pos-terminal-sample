package com.sample.pointofsale.model;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The entire order containing an order id, total, and line items contained
 * @author Lucas Anderson
 */
public class Order {
    /**
     * The order id of the order
     */
    private String orderId;

    /**
     * The total of the order
     */
    private Price total;

    /**
     * The items in the order
     */
    private Map<String, LineItem> items;

    /**
     * Construct a new order
     * @param orderId The order id
     */
    public Order(String orderId) {
        setOrderId(orderId);
        items = new LinkedHashMap<>();
        total = new Price("0.00");
    }

    /**
     * Add a product to the order
     * @param productCode The product code of the product to add
     */
    public void add(String productCode) {
        add(productCode, 1);
    }

    /**
     * Add a product to the order
     * @param productCode The product code of the product to add
     * @param quantity The quantity to add
     */
    public void add(String productCode, int quantity) {
        LineItem item = (items.containsKey(productCode) ? items.get(productCode) : new LineItem(productCode, 0));
        item.setQuantity(item.getQuantity() + quantity);
        items.put(productCode, item);
    }

    /**
     * Remove a product from the order
     * Only removes a single quantity of the product
     * @param productCode The product code of the product to remove
     */
    public void remove(String productCode) {
        remove(productCode, 1);
    }

    /**
     * Remove a product from the order
     * @param productCode The product code of the product to remove
     * @param quantity The quantity to remove
     */
    public void remove(String productCode, int quantity) {
        LineItem item = items.get(productCode);
        if (item != null) {
            if (item.getQuantity() > quantity) {
                item.setQuantity(item.getQuantity() - quantity);
            } else {
                items.remove(productCode);
            }
        }
    }

    /**
     * Retrieve the items in the order
     * @return The items in the order
     */
    public Collection<LineItem> getItems() {
        return items.values();
    }

    /**
     * @return The order id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId The order id to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return The total
     */
    public Price getTotal() {
        return total;
    }

    /**
     * @param total The total to set
     */
    public void setTotal(Price total) {
        this.total = total;
    }
}
