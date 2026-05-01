package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.DepartmentRequest;
import com.billing.shortcourse.dto.DepartmentResponse;
import com.billing.shortcourse.entity.Department;
import com.billing.shortcourse.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> getAll() {
        return departmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse save(DepartmentRequest request) {
        Department dept = new Department();
        dept.setName(request.getName());
        return mapToResponse(departmentRepository.save(dept));
    }

    public DepartmentResponse update(Integer id, DepartmentRequest request) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        dept.setName(request.getName());
        return mapToResponse(departmentRepository.save(dept));
    }

    private DepartmentResponse mapToResponse(Department dept) {
        return DepartmentResponse.builder()
                .id(dept.getId())
                .name(dept.getName())
                .createdAt(dept.getCreatedAt())
                .updatedAt(dept.getUpdatedAt())
                .build();
    }
}