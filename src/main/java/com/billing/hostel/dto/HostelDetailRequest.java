package com.billing.hostel.dto;

import lombok.Data;

@Data
public class HostelDetailRequest {
    private Long hostelId;
    private String name;
    private Integer levelId;
    private Integer intakeId;
    private Integer genderId;
}