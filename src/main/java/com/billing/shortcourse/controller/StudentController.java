package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.StudentDTO;
import com.billing.shortcourse.entity.Student;
import com.billing.shortcourse.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasAuthority('STUDENT_READ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STUDENT_READ') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Student> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('STUDENT_CREATE') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Student> create(@RequestBody StudentDTO dto) {
        Student saved = studentService.createStudent(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('STUDENT_UPDATE') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Student> update(@PathVariable Integer id, @RequestBody StudentDTO dto) {
        Student updated = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STUDENT_DELETE') or hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}