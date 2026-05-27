package com.billing.shortcourse.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItakeResponseDTO {

    private Integer id;
    private Integer courseId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}