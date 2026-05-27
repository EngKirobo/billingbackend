package com.billing.shortcourse.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {

    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer deptId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}