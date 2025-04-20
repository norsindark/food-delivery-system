package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.entities.Role;
import com.fooddelivery.authservice.exceptions.RoleNotFoundException;
import com.fooddelivery.authservice.repositories.RoleRepository;
import com.fooddelivery.authservice.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private static final String DEFAULT_ROLE = "CUSTOMER";

    @Override
    public boolean exitsByRoleName(String roleName) {
        return roleRepository.existsByRoleName(roleName);
    }

    @Override
    public Role getDefaultRole() {
        return roleRepository.findByRoleName(DEFAULT_ROLE)
                .orElseThrow(() -> new RoleNotFoundException("Role not found!"));
    }
}
