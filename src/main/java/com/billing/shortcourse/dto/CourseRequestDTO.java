package com.billing.shortcourse.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private Integer deptId;
}