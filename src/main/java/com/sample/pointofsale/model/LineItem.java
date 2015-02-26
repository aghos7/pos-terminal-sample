package com.sample.pointofsale.model;

/**
 * Represents a line item in an order including product code, quantity and sub-total
 * @author Lucas Anderson
 */
public class LineItem {
    
    /**
     * The product code for this line item
     */
    private String productCode;
    
    /**
     * The quantity of of the product for this line item
     */
    private int quantity;
    
    /**
     * The sub-total for this line item
     */
    private Price subTotal;

    /**
     * Construct a line item
     * @param productCode The product code
     * @param quantity The quantity
     */
    public LineItem(String productCode, int quantity) {
        setProductCode(productCode);
        setQuantity(quantity);
        setSubTotal(new Price("0.00"));
    }

    /**
     * @return The quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity The quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return The sub-total
     */
    public Price getSubTotal() {
        return subTotal;
    }

    /**
     * @param subTotal The sub-total to set
     */
    public void setSubTotal(Price subTotal) {
        this.subTotal = subTotal;
    }

    /**
     * @return The product code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * @param productCode The product code to set
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
