package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.CourseRequestDTO;
import com.billing.shortcourse.dto.CourseResponseDTO;
import com.billing.shortcourse.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")

@RequiredArgsConstructor

@CrossOrigin("*")

public class CourseController {

    private final CourseService courseService;

    // CREATE COURSE
    @PostMapping

    @PreAuthorize("hasAuthority('DEPT_READ')")

    public ResponseEntity<CourseResponseDTO> createCourse(
            @RequestBody CourseRequestDTO request
    ) {

        return new ResponseEntity<>(

                courseService.createCourse(request),

                HttpStatus.CREATED
        );
    }

    // UPDATE COURSE
    @PutMapping("/{id}")

    @PreAuthorize("hasAuthority('DEPT_UPDATE')")

    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Integer id,

            @RequestBody CourseRequestDTO request
    ) {

        return ResponseEntity.ok(

                courseService.updateCourse(id, request)
        );
    }

    // GET COURSE BY ID
    @GetMapping("/{id}")

    @PreAuthorize("hasAuthority('DEPT_READ')")

    public ResponseEntity<CourseResponseDTO> getCourseById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(

                courseService.getCourseById(id)
        );
    }

    // GET ALL COURSES
    @GetMapping

    @PreAuthorize("hasAuthority('DEPT_READ')")

    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {

        return ResponseEntity.ok(

                courseService.getAllCourses()
        );
    }

    // GET COURSES BY DEPARTMENT
    @GetMapping("/department/{deptId}")

    @PreAuthorize("hasAuthority('DEPT_READ')")

    public ResponseEntity<List<CourseResponseDTO>>
    getCoursesByDepartment(
            @PathVariable Integer deptId
    ) {

        return ResponseEntity.ok(

                courseService.getCoursesByDepartment(deptId)
        );
    }

    // DELETE COURSE
    @DeleteMapping("/{id}")

    @PreAuthorize("hasAuthority('DEPT_DELETE')")

    public ResponseEntity<Void> deleteCourse(
            @PathVariable Integer id
    ) {

        courseService.deleteCourse(id);

        return ResponseEntity.noContent().build();
    }
}