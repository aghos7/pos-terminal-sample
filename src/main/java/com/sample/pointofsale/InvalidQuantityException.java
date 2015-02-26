package com.sample.pointofsale;

/**
 * @author Lucas Anderson 
 *
 */
public class InvalidQuantityException extends Exception {
    private static final long serialVersionUID = 3674824542289673519L;
    /**
     * @param message
     */
    public InvalidQuantityException(String message) {
        super(message);
    }
}
