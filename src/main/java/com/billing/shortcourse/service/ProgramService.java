package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.DepartmentResponse;
import com.billing.shortcourse.dto.ProgramRequest;
import com.billing.shortcourse.dto.ProgramResponse;
import com.billing.shortcourse.entity.Department;
import com.billing.shortcourse.entity.Program;
import com.billing.shortcourse.repository.DepartmentRepository;
import com.billing.shortcourse.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final DepartmentRepository departmentRepository;

    public List<ProgramResponse> getAll() {
        return programRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public ProgramResponse save(ProgramRequest request) {
        Department dept = departmentRepository.findById(request.getDeptId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Program program = new Program();
        program.setName(request.getName());
        program.setDepartment(dept);
        
        return mapToResponse(programRepository.save(program));
    }

    private ProgramResponse mapToResponse(Program program) {
        // // DepartmentResponse deptDto = null;
        // if (program.getDepartment() != null) {
        //     deptDto = DepartmentResponse.builder()
        //             .id(program.getDepartment().getId())
        //             .name(program.getDepartment().getName())
        //             .build();
        // }

        return ProgramResponse.builder()
                .id(program.getId())
                .name(program.getName())
                // .department(deptDto)
                .createdAt(program.getCreatedAt())
                .updatedAt(program.getUpdatedAt())
                .build();
    }

    public ProgramResponse getById(Integer id) {
    Program program = programRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Program not found with id: " + id));
    return mapToResponse(program);
}

    public ProgramResponse update(Integer id, ProgramRequest request) {
        // 1. Check if the program exists
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + id));
        
        // 2. Check if the new department ID provided in the request exists
        Department dept = departmentRepository.findById(request.getDeptId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + request.getDeptId()));

        // 3. Update the entity fields
        program.setName(request.getName());
        program.setDepartment(dept);
        
        // 4. Save and return the mapped response
        return mapToResponse(programRepository.save(program));
        }
        public void delete(Integer id) {
            // Check if it exists first so we can throw a meaningful error
            if (!programRepository.existsById(id)) {
                throw new RuntimeException("Cannot delete: Program not found with id: " + id);
            }
            programRepository.deleteById(id);
        }
}