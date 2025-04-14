package com.fooddelivery.authservice.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorValidationResponse {
    private String field;
    private String message;
}
