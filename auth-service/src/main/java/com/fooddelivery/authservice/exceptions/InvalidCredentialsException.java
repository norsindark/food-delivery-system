package com.fooddelivery.authservice.exceptions;

public class InvalidCredentialsException extends AppException{

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
