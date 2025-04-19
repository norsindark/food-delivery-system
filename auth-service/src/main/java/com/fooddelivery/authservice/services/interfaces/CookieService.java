package com.fooddelivery.authservice.services.interfaces;

import jakarta.servlet.http.HttpServletResponse;

public interface CookieService {
    void setRefreshToken(HttpServletResponse httpServletResponse, String refreshToken);
}
