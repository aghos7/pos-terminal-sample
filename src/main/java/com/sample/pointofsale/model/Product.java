package com.sample.pointofsale.model;

/**
 * A product in the system
 * @note It is mostly unused as the current requirements do not really need products, only prices
 * @author Lucas Anderson
 */
public class Product {
    /**
     * The product code of the product
     */
    private String code;

    /**
     * The name of the product
     */
    private String name;

    /**
     * A description of the product
     */
    private String description;

    /**
     * Construct a product
     * @param code The product code
     */
    public Product(String code) {
        setCode(code);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
