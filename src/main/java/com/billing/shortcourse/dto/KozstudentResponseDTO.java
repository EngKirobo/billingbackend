package com.billing.shortcourse.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KozstudentResponseDTO {

    private Integer id;

    private String fullname;

    private String coursename;

    private Float courseprice;

    private LocalDateTime createdAt;

    private Boolean accessed;
}