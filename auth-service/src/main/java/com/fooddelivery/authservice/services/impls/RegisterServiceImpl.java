package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.entities.Role;
import com.fooddelivery.authservice.entities.User;
import com.fooddelivery.authservice.enums.RoleName;
import com.fooddelivery.authservice.exceptions.DataExitsException;
import com.fooddelivery.authservice.payloads.requests.RegisterRequest;
import com.fooddelivery.authservice.payloads.responses.ApiResponse;
import com.fooddelivery.authservice.services.interfaces.RegisterService;
import com.fooddelivery.authservice.services.interfaces.RoleService;
import com.fooddelivery.authservice.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;

    @Override
    public ApiResponse<?> register(RegisterRequest registerRequest) {
        emailAlreadyExits(registerRequest.getEmail(), registerRequest.getUsername());
        Role role = getDefaulRole();
        userService.saveUser(createNewUser(registerRequest, role));

        return ApiResponse.builder()
                .status("success")
                .message("User registered successfully!")
                .error(null)
                .data(null)
                .build();
    }

    private User createNewUser(RegisterRequest request, Role role) {
        return User.builder()
                .email(request.getEmail())
                .username(request.getUsername())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(role)
                .providerId("local")
                .provider(null)
                .build();
    }

    private Role getDefaulRole() {
        return roleService.getDefaultRole();
    }

    private void emailAlreadyExits(String email, String username) {
        if (userService.existsByUsernameOrEmail(email, username))
            throw new DataExitsException("Email or Username already exists!");
    }
}
