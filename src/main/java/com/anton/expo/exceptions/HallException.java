package com.anton.expo.exceptions;

public class HallException extends RuntimeException {
    public HallException(String message) {
        super(message);
    }

    public HallException(String message, Throwable cause) {
        super(message, cause);
    }
}
