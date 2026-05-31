package com.billing.shortcourse.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KozstudentRequestDTO {

    private String fullname;

    private String coursename;

    private Float courseprice;

    private Boolean accessed;
}