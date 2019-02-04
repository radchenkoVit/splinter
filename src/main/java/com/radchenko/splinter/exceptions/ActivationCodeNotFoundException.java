package com.radchenko.splinter.exceptions;

public class ActivationCodeNotFoundException extends RuntimeException {
    public ActivationCodeNotFoundException() {
    }

    public ActivationCodeNotFoundException(String message) {
        super(message);
    }

    public ActivationCodeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
