package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.entities.User;
import com.fooddelivery.authservice.exceptions.InvalidCredentialsException;
import com.fooddelivery.authservice.payloads.requests.LoginRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.repositories.UserRepository;
import com.fooddelivery.authservice.services.interfaces.CookieService;
import com.fooddelivery.authservice.services.interfaces.LoginService;
import com.fooddelivery.authservice.services.interfaces.TokenService;
import com.fooddelivery.authservice.services.interfaces.UserService;
import com.fooddelivery.authservice.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final TokenService tokenService;
    private final CookieService cookieService;
    private final UserService userService;

    @Override
    public ApiResponse<?> login(LoginRequest request, HttpServletResponse httpServletResponse) {
        User user = getUser(request.getIdentifier());
        validatePassword(request.getPassword(), user.getPassword());
        authenticateUser(request);

        String accessToken = tokenService.generateAccessToken(request.getIdentifier(), user.getRole().getRoleName());
        String refreshToken = tokenService.generateRefreshToken(request.getIdentifier());

        tokenService.storeRefreshTokenToRedis(request.getIdentifier(), refreshToken);
        cookieService.setRefreshToken(httpServletResponse, refreshToken);

        return ApiResponse.builder()
                .status("Success")
                .message("Login successfully!")
                .data(Collections.singletonMap("accessToken", accessToken))
                .error(null)
                .build();
    }

    private User getUser(String identifier) {
        return userService.findUserByUsernameOrEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw new InvalidCredentialsException("Invalid login credentials");
    }

    private void authenticateUser(LoginRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getIdentifier(),
                        request.getPassword()
                ));
    }
}
