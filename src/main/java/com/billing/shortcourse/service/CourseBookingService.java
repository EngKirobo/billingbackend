package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.CourseBookingRequestDTO;
import com.billing.shortcourse.dto.CourseBookingResponseDTO;

import java.util.List;

public interface CourseBookingService {

    CourseBookingResponseDTO createBooking(CourseBookingRequestDTO requestDTO);

    List<CourseBookingResponseDTO> getAllBookings();

    CourseBookingResponseDTO getBookingById(Integer id);

    List<CourseBookingResponseDTO> getBookingsByStudent(Integer studId);

    List<CourseBookingResponseDTO> getBookingsByIntake(Integer intakeId);

    CourseBookingResponseDTO updateBooking(
            Integer id,
            CourseBookingRequestDTO requestDTO
    );

    void deleteBooking(Integer id);
}