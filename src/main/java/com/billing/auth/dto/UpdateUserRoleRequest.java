package com.billing.auth.dto;

import lombok.Data;

@Data
public class UpdateUserRoleRequest {
    private Long userId;
    private Long roleId;
}