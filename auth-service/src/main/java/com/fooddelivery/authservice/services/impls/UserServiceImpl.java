package com.fooddelivery.authservice.services.impls;

import com.fooddelivery.authservice.entities.User;
import com.fooddelivery.authservice.repositories.UserRepository;
import com.fooddelivery.authservice.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean existsByUsernameOrEmail(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByUsernameOrEmail(String identifier) {
        return userRepository.findByEmailOrUsername(identifier, identifier);
    }
}
