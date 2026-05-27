package com.billing.shortcourse.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseBookingResponseDTO {

    private Integer id;

    private Integer intakeId;

    private Integer studId;

    private Boolean verified;

    private Boolean allowed;

    private Boolean ctn;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}