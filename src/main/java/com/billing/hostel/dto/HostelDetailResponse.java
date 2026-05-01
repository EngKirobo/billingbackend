package com.billing.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostelDetailResponse {
    private Long id;
    private Long hostelId;
    // private String hostelName;        // For better readability
    private String name;
    private Integer levelId;
    private Integer intakeId;
    private Integer genderId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}