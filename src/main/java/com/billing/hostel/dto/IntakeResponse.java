package com.billing.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO for returning Intake information.
 * Mapped from the 'intakes' table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IntakeResponse {

    private Long id;
    
    private int intake;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}