package com.fooddelivery.authservice.repositories;

import com.fooddelivery.authservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    boolean existsByRoleName(String roleName);
    
    Optional<Role> findByRoleName(String roleName);
}
