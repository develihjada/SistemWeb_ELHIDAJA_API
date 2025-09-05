package com.hidajasac.hidajasac_apis.service.exeption;

public class InvalidDocumentException extends RuntimeException{
    public InvalidDocumentException(String message) {
        super(message);
    }
}
