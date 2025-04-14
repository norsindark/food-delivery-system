package com.fooddelivery.authservice.utils;

import com.fooddelivery.authservice.entities.Role;
import com.fooddelivery.authservice.entities.User;
import com.fooddelivery.authservice.repositories.RoleRepository;
import com.fooddelivery.authservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            Role customerRole = Role.builder().roleName("CUSTOMER").build();
            Role restaurantOwnerRole = Role.builder().roleName("RESTAURANT_OWNER").build();
            Role deliveryPersonRole = Role.builder().roleName("DELIVERY_PERSON").build();
            Role adminRole = Role.builder().roleName("ADMIN").build();

            roleRepository.saveAll(Arrays.asList(customerRole, restaurantOwnerRole, deliveryPersonRole, adminRole));
        }

        if (userRepository.findByEmailOrUsername("norsindark@gmail.com", "ntuan").isEmpty()) {
            Role adminRole = roleRepository.findByRoleName("ADMIN").orElseThrow(
                    () -> new RuntimeException("Role Admin not found!"));
            User admin = User.builder()
                    .email("norsindark@gmail.com")
                    .role(adminRole)
                    .fullName("Nguyen Tuan")
                    .username("ntuan")
                    .password(encoder.encode("05022001"))
                    .providerId("local")
                    .build();
            userRepository.save(admin);
            System.out.println("✅ Default admin user created.");
        }
    }

}
