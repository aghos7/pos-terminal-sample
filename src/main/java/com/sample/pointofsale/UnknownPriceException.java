package com.sample.pointofsale;

/**
 * @author Lucas Anderson 
 *
 */
public class UnknownPriceException extends Exception {
    private static final long serialVersionUID = 7206521610229020648L;

    /**
     * @param message
     */
    public UnknownPriceException(String message) {
        super(message);
    }
}
