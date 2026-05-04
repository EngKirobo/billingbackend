package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.ProgramRequest;
import com.billing.shortcourse.dto.ProgramResponse;
import com.billing.shortcourse.entity.Program;
import com.billing.shortcourse.repository.ProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;

    // ✅ GET ALL
    public List<ProgramResponse> getAll() {
        return programRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ CREATE
    public ProgramResponse save(ProgramRequest request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new RuntimeException("Program name is required");
        }

        if (request.getDeptId() == null) {
            throw new RuntimeException("deptId is required");
        }

        Program program = new Program();
        program.setName(request.getName());
        program.setDeptId(request.getDeptId());

        return mapToResponse(programRepository.save(program));
    }

    // ✅ GET BY ID
    public ProgramResponse getById(Integer id) {
        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + id));

        return mapToResponse(program);
    }

    // ✅ UPDATE
    public ProgramResponse update(Integer id, ProgramRequest request) {

        Program program = programRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Program not found with id: " + id));

        if (request.getName() != null) {
            program.setName(request.getName());
        }

        if (request.getDeptId() != null) {
            program.setDeptId(request.getDeptId());
        }

        return mapToResponse(programRepository.save(program));
    }

    // ✅ DELETE
    public void delete(Integer id) {
        if (!programRepository.existsById(id)) {
            throw new RuntimeException("Cannot delete: Program not found with id: " + id);
        }
        programRepository.deleteById(id);
    }

    // ✅ MAPPER
    private ProgramResponse mapToResponse(Program program) {
        return ProgramResponse.builder()
                .id(program.getId())
                .name(program.getName())
                .deptId(program.getDeptId())
                .createdAt(program.getCreatedAt())
                .updatedAt(program.getUpdatedAt())
                .build();
    }
}