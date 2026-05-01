package com.billing.shortcourse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String admino;

    private String email;
    private String country;
    private LocalDate dob;

    private Integer genderId;
    private Integer entryId;
    private Integer intakeId;

    private String telephone;

    private Integer programId; // 👈 important
}