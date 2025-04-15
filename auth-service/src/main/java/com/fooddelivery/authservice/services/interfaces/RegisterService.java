package com.fooddelivery.authservice.services.interfaces;

import com.fooddelivery.authservice.payloads.requests.RegisterRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;

public interface RegisterService {
    ApiResponse<?> register(RegisterRequest registerRequest);
}
