package com.fooddelivery.authservice.exceptions;

public class InvalidOAuthTokenException extends AppException{

    public InvalidOAuthTokenException(String message) {
        super(message);
    }
}
