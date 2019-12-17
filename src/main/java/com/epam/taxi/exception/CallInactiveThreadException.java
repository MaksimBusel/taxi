package com.epam.taxi.exception;

public class CallInactiveThreadException extends RuntimeException {

    public CallInactiveThreadException(String message, Throwable cause) {
        super(message, cause);
    }

    public CallInactiveThreadException(String message) {
        super(message);
    }

    public CallInactiveThreadException() {
    }
}

