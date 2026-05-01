package com.billing.shortcourse.dto;

import lombok.Data;

@Data
public class ProgramRequest {
    private String name;
    private Integer deptId; // The ID used to link to the Department
}