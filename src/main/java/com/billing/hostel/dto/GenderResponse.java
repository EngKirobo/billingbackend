package com.billing.hostel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GenderResponse {
    private Integer id;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}