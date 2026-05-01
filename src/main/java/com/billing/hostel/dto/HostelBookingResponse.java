package com.billing.hostel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HostelBookingResponse {

    private Integer id;

    private Integer roomId;
    private Integer studentId;

    private String academicYear;
    private String semester;

    private Boolean verified;
    private Boolean allowed;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}