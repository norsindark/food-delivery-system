package com.fooddelivery.authservice.services.interfaces;

import com.fooddelivery.authservice.payloads.requests.LoginRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface LoginService {
    ApiResponse<?> login(LoginRequest request, HttpServletResponse httpServletResponse);
}
