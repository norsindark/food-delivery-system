package com.fooddelivery.authservice.services.interfaces;

import com.fooddelivery.authservice.entities.User;

import java.util.Optional;

public interface UserService {
    boolean existsByUsernameOrEmail(String username, String email);

    User saveUser(User user);

    Optional<User> findUserByUsernameOrEmail(String identifier);
}
