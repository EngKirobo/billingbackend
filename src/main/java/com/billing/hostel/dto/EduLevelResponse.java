
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
public class EduLevelResponse {
    private Integer id;
    private String eduLevel;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}