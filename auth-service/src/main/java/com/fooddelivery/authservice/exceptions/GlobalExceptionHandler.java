package com.fooddelivery.authservice.exceptions;

import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.payloads.responses.ErrorResponse;
import com.fooddelivery.authservice.payloads.responses.ErrorValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<?>> handleAppException(AppException ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code("BAD_REQUEST")
                .description(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status("400 Bad request")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .status("error")
                        .message(ex.getMessage())
                        .data(null)
                        .error(response)
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleOtherException(Exception ex) {
        ErrorResponse response = ErrorResponse.builder()
                .code("INTERNAL_ERROR")
                .description("An unexpected error occurred.")
                .timestamp(LocalDateTime.now())
                .status("500 Internal Server Error")
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .status("error")
                        .message(ex.getMessage())
                        .data(null)
                        .error(response)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<ErrorValidationResponse> errorValidateResponses = new ArrayList<>();

        for (FieldError fieldError : result.getFieldErrors()) {
            ErrorValidationResponse response = new ErrorValidationResponse(fieldError.getField(), fieldError.getDefaultMessage());
            errorValidateResponses.add(response);
        }

        ErrorResponse response = ErrorResponse.builder().code("VALIDATION_ERROR")
                .description("Some fields are missing or incorrect")
                .timestamp(LocalDateTime.now())
                .details(errorValidateResponses)
                .status("400 Bad Request")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .status("error")
                        .message("Validation failed")
                        .data(null)
                        .error(response)
                        .build());
    }
}