package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.StudentRequest;
import com.billing.shortcourse.dto.StudentResponse;
import com.billing.shortcourse.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PreAuthorize("hasAuthority('STUDENTS_CREATE')")
    @PostMapping
    public StudentResponse create(@RequestBody StudentRequest request) {
        return studentService.create(request);
    }

    @PreAuthorize("hasAuthority('STUDENTS_READ')")
    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAll();
    }

    @PreAuthorize("hasAuthority('STUDENTS_READ')")
    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Integer id) {
        return studentService.getById(id);
    }

    @PreAuthorize("hasAuthority('STUDENTS_UPDATE')")
    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Integer id,
                                  @RequestBody StudentRequest request) {
        return studentService.update(id, request);
    }

    @PreAuthorize("hasAuthority('STUDENTS_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        studentService.delete(id);
    }
}