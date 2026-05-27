package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.CourseRequestDTO;
import com.billing.shortcourse.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CourseRequestDTO request);

    CourseResponseDTO updateCourse(Integer id, CourseRequestDTO request);

    CourseResponseDTO getCourseById(Integer id);

    List<CourseResponseDTO> getAllCourses();

    List<CourseResponseDTO> getCoursesByDepartment(Integer deptId);

    void deleteCourse(Integer id);
}