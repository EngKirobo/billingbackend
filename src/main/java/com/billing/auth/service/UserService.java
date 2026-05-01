package com.billing.auth.service;

import com.billing.auth.entity.User;
import com.billing.auth.entity.Role;
import com.billing.auth.repository.UserRepository;
import com.billing.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User updateUserRole(Long userId, Long roleId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        user.setRole(role);

        return userRepository.save(user);
    }
}