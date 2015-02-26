package com.sample.pointofsale;

/**
 * @author Lucas Anderson 
 *
 */
public class UnknownProductException extends Exception {
    private static final long serialVersionUID = 8809973520574064655L;
    
    /**
     * @param message
     */
    public UnknownProductException(String message) {
        super(message);
    }
}
