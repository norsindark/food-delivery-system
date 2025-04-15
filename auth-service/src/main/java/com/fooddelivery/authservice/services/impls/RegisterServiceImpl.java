package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.entities.Role;
import com.fooddelivery.authservice.entities.User;
import com.fooddelivery.authservice.exceptions.DataExitsException;
import com.fooddelivery.authservice.exceptions.RoleNotFoundException;
import com.fooddelivery.authservice.payloads.requests.RegisterRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.repositories.RoleRepository;
import com.fooddelivery.authservice.repositories.UserRepository;
import com.fooddelivery.authservice.services.interfaces.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new DataExitsException("Email already exists!");

        Role role = roleRepository.findByRoleName("CUSTOMER")
                .orElseThrow(() -> new RoleNotFoundException("Role not found!"));

        User newUser = User.builder()
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .fullName(registerRequest.getFullName())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .providerId("local")
                .provider(null)
                .build();
        userRepository.save(newUser);

        return ApiResponse.builder()
                .status("success")
                .message("User registered successfully!")
                .error(null)
                .data(null)
                .build();
    }
}
