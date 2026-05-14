package com.billing.shortcourse.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;


@Data
@Builder
public class ProgramResponse {
    private Integer id;
    private String name;
    private Integer deptId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}