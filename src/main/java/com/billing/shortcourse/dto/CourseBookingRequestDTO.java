package com.billing.shortcourse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseBookingRequestDTO {

    private Integer intakeId;

    private Integer studId;

    private Boolean verified;

    private Boolean allowed;

    private Boolean ctn;
}