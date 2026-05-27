package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.dto.CourseRequestDTO;
import com.billing.shortcourse.dto.CourseResponseDTO;
import com.billing.shortcourse.entity.Course;
import com.billing.shortcourse.repository.CourseRepository;
import com.billing.shortcourse.service.CourseService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public CourseResponseDTO createCourse(
            CourseRequestDTO request
    ) {

        Course course = Course.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .deptId(request.getDeptId())
                .build();

        Course savedCourse =
                courseRepository.save(course);

        return mapToResponseDTO(savedCourse);
    }

    @Override
    public CourseResponseDTO updateCourse(
            Integer id,
            CourseRequestDTO request
    ) {

        Course course =
                courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setPrice(request.getPrice());
        course.setDeptId(request.getDeptId());

        Course updatedCourse =
                courseRepository.save(course);

        return mapToResponseDTO(updatedCourse);
    }

    @Override
    public CourseResponseDTO getCourseById(
            Integer id
    ) {

        Course course =
                courseRepository.findById(id).orElse(null);

        if (course == null) {
            return null;
        }

        return mapToResponseDTO(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseResponseDTO> getCoursesByDepartment(
            Integer deptId
    ) {

        return courseRepository.findByDeptId(deptId)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCourse(Integer id) {

        if (courseRepository.existsById(id)) {

            courseRepository.deleteById(id);

        }
    }

    private CourseResponseDTO mapToResponseDTO(
            Course course
    ) {

        return CourseResponseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .price(course.getPrice())
                .deptId(course.getDeptId())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}