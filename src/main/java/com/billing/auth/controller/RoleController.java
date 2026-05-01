package com.billing.auth.controller;

import com.billing.auth.dto.UpdateRolePermissionsRequest;
import com.billing.auth.entity.Role;
import com.billing.auth.entity.Permission;
import com.billing.auth.repository.RoleRepository;
import com.billing.auth.repository.PermissionRepository;
import com.billing.auth.service.RoleService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/roles")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    @PutMapping("/update-permissions")
    // 🔐 protect this
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public Role updatePermissions(@RequestBody UpdateRolePermissionsRequest request) {

        return roleService.updateRolePermissions(
                request.getRoleId(),
                request.getPermissionIds()
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/permissions")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }
}