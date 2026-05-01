package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.StudentRequest;
import com.billing.shortcourse.dto.StudentResponse;
import com.billing.shortcourse.entity.Program;
import com.billing.shortcourse.entity.Student;
import com.billing.shortcourse.repository.ProgramRepository;
import com.billing.shortcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProgramRepository programRepository;

    public StudentResponse create(StudentRequest request) {

        if (studentRepository.existsByAdmino(request.getAdmino())) {
            throw new RuntimeException("Admin number already exists");
        }

        Program program = null;
        if (request.getProgramId() != null) {
            program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program not found"));
        }

        Student student = Student.builder()
                .name(request.getName())
                .admino(request.getAdmino())
                .email(request.getEmail())
                .country(request.getCountry())
                .dob(request.getDob())
                .genderId(request.getGenderId())
                .entryId(request.getEntryId())
                .intakeId(request.getIntakeId())
                .telephone(request.getTelephone())
                .program(program)
                .build();

        return mapToResponse(studentRepository.save(student));
    }

    public List<StudentResponse> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StudentResponse getById(Integer id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return mapToResponse(student);
    }

    public StudentResponse update(Integer id, StudentRequest request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setCountry(request.getCountry());
        student.setDob(request.getDob());
        student.setTelephone(request.getTelephone());

        if (request.getProgramId() != null) {
            Program program = programRepository.findById(request.getProgramId())
                    .orElseThrow(() -> new RuntimeException("Program not found"));
            student.setProgram(program);
        }

        return mapToResponse(studentRepository.save(student));
    }

    public void delete(Integer id) {
        studentRepository.deleteById(id);
    }

    // ✅ Mapper
    private StudentResponse mapToResponse(Student s) {
        return StudentResponse.builder()
                .id(s.getId())
                .name(s.getName())
                .admino(s.getAdmino())
                .email(s.getEmail())
                .country(s.getCountry())
                .dob(s.getDob())
                .telephone(s.getTelephone())
                .programId(s.getProgram() != null ? s.getProgram().getId() : null)
                // .programName(s.getProgram() != null ? s.getProgram().getName() : null)
                .createdAt(s.getCreatedAt())
                .updatedAt(s.getUpdatedAt())
                .build();
    }
}