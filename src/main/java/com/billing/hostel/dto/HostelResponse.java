package com.billing.hostel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostelResponse {
    private Long id;
    private String name;
    private String description;
    private String location;
    private Integer capacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}