package com.sample.pointofsale.model;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Represents a price in the system
 * Contains a currency and amount
 * @note Currently the currency is unused
 * @author Lucas Anderson
 */
public class Price {
    private Currency currency;
    private BigDecimal amount;

    /**
     * Construct a price
     * @param amount String representation of the amount
     */
    public Price(String amount) {
        this(new BigDecimal(amount));
    }

    /**
     * Construct a price
     * @param amount BigDecimal representation of the amount
     */
    public Price(BigDecimal price) {
        setAmount(price);
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount The amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the currency
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency The currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
