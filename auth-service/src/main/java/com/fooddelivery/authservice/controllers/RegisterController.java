package com.fooddelivery.authservice.controllers;

import com.fooddelivery.authservice.payloads.requests.RegisterRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.services.interfaces.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Register", description = "Register API")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(registerService.register(registerRequest));
    }
}
