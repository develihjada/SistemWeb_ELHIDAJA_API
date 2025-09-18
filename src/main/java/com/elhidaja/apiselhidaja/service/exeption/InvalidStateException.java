package com.elhidaja.apiselhidaja.service.exeption;

public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String message) {
        super(message);
    }
}
