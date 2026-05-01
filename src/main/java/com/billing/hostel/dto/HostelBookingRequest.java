package com.billing.hostel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HostelBookingRequest {

    @NotNull
    private Integer roomId;

    @NotNull
    private Integer studentId;

    @NotBlank
    private String academicYear;

    @NotBlank
    private String semester;

    private Boolean verified;
    private Boolean allowed;
}