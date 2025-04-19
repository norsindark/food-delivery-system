package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.configs.JwtPropertiesConfig;
import com.fooddelivery.authservice.services.interfaces.CookieService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CookieServiceImpl implements CookieService {
    private final JwtPropertiesConfig jwtPropertiesConfig;

    @Override
    public void setRefreshToken(HttpServletResponse httpServletResponse, String refreshToken) {
        long expirationMs = jwtPropertiesConfig.getRefreshTokenExpirationMs();
        int expirationSec = (int) TimeUnit.MILLISECONDS.toSeconds(expirationMs);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(expirationSec);
    }
}
