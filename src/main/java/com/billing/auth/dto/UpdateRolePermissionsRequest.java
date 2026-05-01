package com.billing.auth.dto;


import lombok.Data;
import java.util.List;

@Data
public class UpdateRolePermissionsRequest {
    private Long roleId;
    private List<Long> permissionIds;
}