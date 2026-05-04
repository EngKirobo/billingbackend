package com.billing.shortcourse.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.billing.shortcourse.entity.Program;

@Data
@Builder
public class ProgramResponse {
    private Integer id;
    private String name;
    private Integer deptId;
    //private DepartmentResponse department; // Nested response for clarity
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}