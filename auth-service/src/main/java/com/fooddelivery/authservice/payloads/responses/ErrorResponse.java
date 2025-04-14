package com.fooddelivery.authservice.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String code;
    private String description;
    private LocalDateTime timestamp;
    private List<ErrorValidationResponse> details;
    private String status;
}
