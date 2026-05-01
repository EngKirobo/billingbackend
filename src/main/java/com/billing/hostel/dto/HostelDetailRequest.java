package com.billing.hostel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostelDetailRequest {

    @NotNull(message = "Hostel ID is required")
    private Long hostelId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Level ID is required")
    private Integer levelId;

    @NotNull(message = "Intake ID is required")
    private Integer intakeId;

    @NotNull(message = "Gender ID is required")
    private Integer genderId;
}