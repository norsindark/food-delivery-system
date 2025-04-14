package com.fooddelivery.authservice.exceptions;

public class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
