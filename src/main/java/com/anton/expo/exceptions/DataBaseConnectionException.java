package com.anton.expo.exceptions;

/**
 * Database connection Exception
 */
public class DataBaseConnectionException extends RuntimeException {
    public DataBaseConnectionException(String message) {
        super(message);
    }

    public DataBaseConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
