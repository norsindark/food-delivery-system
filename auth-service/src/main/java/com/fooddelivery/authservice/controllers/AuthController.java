package com.fooddelivery.authservice.controllers;

import com.fooddelivery.authservice.payloads.requests.LoginRequest;
import com.fooddelivery.authservice.payloads.requests.RegisterRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.services.interfaces.LoginService;
import com.fooddelivery.authservice.services.interfaces.RegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication APIs")
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(
            @Valid @RequestBody RegisterRequest registerRequest) {
        ApiResponse<?> result = registerService.register(registerRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<?>> login(
            @Valid @RequestBody LoginRequest loginRequest, HttpServletResponse httpServletResponse) {
        ApiResponse<?> result = loginService.login(loginRequest, httpServletResponse);
        return ResponseEntity.ok(result);
    }
}
