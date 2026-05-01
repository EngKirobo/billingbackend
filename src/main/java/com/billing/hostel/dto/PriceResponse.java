package com.billing.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {
    private Integer id;
    private Float amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}