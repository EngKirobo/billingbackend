package com.billing.shortcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class StudentResponse {

    private Integer id;
    private String name;
    private String admino;
    private String email;
    private String country;
    private LocalDate dob;
    private String telephone;
    private Integer genderId;
    private Integer entryId;
    private Integer intakeId;

    private Integer programId;
    // private String programName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}