package com.fooddelivery.authservice.repositories;

import com.fooddelivery.authservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmailOrUsername(String email, String username);

    boolean existsByEmail(String email);
}
