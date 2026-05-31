package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.KozstudentRequestDTO;
import com.billing.shortcourse.dto.KozstudentResponseDTO;
import com.billing.shortcourse.entity.Kozstudent;
import com.billing.shortcourse.repository.KozstudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KozstudentService {

    private final KozstudentRepository repository;

    public List<KozstudentResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public KozstudentResponseDTO getById(Integer id) {

        return mapToResponse(
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Student not found"))
        );
    }

    public KozstudentResponseDTO create(
            KozstudentRequestDTO dto
    ) {

        Kozstudent student = Kozstudent.builder()
                .fullname(dto.getFullname())
                .coursename(dto.getCoursename())
                .courseprice(dto.getCourseprice())
                .accessed(dto.getAccessed())
                .build();

        return mapToResponse(
                repository.save(student)
        );
    }

    public KozstudentResponseDTO update(
            Integer id,
            KozstudentRequestDTO dto
    ) {

        Kozstudent student = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Student not found"));

        student.setFullname(dto.getFullname());
        student.setCoursename(dto.getCoursename());
        student.setCourseprice(dto.getCourseprice());
        student.setAccessed(dto.getAccessed());

        return mapToResponse(
                repository.save(student)
        );
    }

    public void delete(Integer id) {

        repository.deleteById(id);
    }

    private KozstudentResponseDTO mapToResponse(
            Kozstudent student
    ) {

        return KozstudentResponseDTO.builder()
                .id(student.getId())
                .fullname(student.getFullname())
                .coursename(student.getCoursename())
                .courseprice(student.getCourseprice())
                .createdAt(student.getCreatedAt())
                .accessed(student.getAccessed())
                .build();
    }
}