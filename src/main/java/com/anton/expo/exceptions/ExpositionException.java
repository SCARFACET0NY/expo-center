package com.anton.expo.exceptions;

public class ExpositionException extends RuntimeException {
    public ExpositionException(String message) {
        super(message);
    }

    public ExpositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
