package com.sample.pointofsale.services;

import com.sample.pointofsale.UnknownPriceException;
import com.sample.pointofsale.model.Order;
import com.sample.pointofsale.model.Price;

/**
 * A simple interface for managing orders
 * @author Lucas Anderson
 */
public interface OrderService {
    /**
     * Create a new order
     * @return The new order
     */
    Order newOrder();

    /**
     * Cancels the specified order
     * @param order The order to cancel
     */
    void cancelOrder(Order order);

    /**
     * Calculates an order
     * @param order The order to calculate
     * @return The total price of the order
     * @throws UnknownPriceException No price per quantity is known
     */
    Price calculateOrder(Order order) throws UnknownPriceException;
}
