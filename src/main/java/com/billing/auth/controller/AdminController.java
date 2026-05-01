package com.billing.auth.controller;

import com.billing.auth.dto.UpdateUserRoleRequest;
import com.billing.auth.entity.User;
import com.billing.auth.repository.UserRepository;
import com.billing.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UserRepository userRepository;

    // 🔥 ONLY ADMIN CAN CHANGE ROLE
    @PutMapping("/update-role")
    // @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public User updateUserRole(@RequestBody UpdateUserRoleRequest request) {

        return userService.updateUserRole(
                request.getUserId(),
                request.getRoleId()
        );
    }

    // ✅ GET ALL USERS (ADMIN DASHBOARD)
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER_READ')")     
    // @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}