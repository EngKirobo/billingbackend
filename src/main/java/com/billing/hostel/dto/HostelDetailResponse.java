package com.billing.hostel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HostelDetailResponse {
    private Long id;
    private Long hostelId;
    private String name;
    private Integer levelId;
    private Integer intakeId;
    private Integer genderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}