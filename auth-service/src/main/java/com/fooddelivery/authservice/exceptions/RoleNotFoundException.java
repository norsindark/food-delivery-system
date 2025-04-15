package com.fooddelivery.authservice.exceptions;

public class RoleNotFoundException extends AppException{
    public RoleNotFoundException(String message) {
        super(message);
    }
}
