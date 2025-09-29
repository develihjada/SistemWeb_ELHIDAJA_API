package com.elhidaja.apiselhidaja.service.exeption;

public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(String message) {
        super(message);
    }
}
