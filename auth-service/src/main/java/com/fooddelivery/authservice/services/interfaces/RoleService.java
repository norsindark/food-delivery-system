package com.fooddelivery.authservice.services.interfaces;

import com.fooddelivery.authservice.entities.Role;

public interface RoleService {
    boolean exitsByRoleName(String roleName);

    Role getDefaultRole();
}
