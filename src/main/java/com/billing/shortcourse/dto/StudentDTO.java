package com.billing.shortcourse.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Integer id;
    private String name;
    private String admino;
    private String email;
    private String country;
    private LocalDate dob;
    private Integer genderId;
    private Integer entryId;
    private Integer programId;
    private Integer intakeId;
    private String telephone;
}