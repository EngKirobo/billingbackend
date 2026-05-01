package com.billing.auth.service;

import com.billing.auth.entity.Permission;
import com.billing.auth.entity.Role;
import com.billing.auth.repository.PermissionRepository;
import com.billing.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public Role updateRolePermissions(Long roleId, List<Long> permissionIds) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        List<Permission> permissions = permissionRepository.findAllById(permissionIds);

        role.setPermissions(new HashSet<>(permissions));

        return roleRepository.save(role);
    }
}
