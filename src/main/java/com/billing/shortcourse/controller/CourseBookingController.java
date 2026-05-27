package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.CourseBookingRequestDTO;
import com.billing.shortcourse.dto.CourseBookingResponseDTO;
import com.billing.shortcourse.service.CourseBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coursebookings")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CourseBookingController {

    private final CourseBookingService courseBookingService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_INSERT')")
    public CourseBookingResponseDTO createBooking(
            @RequestBody CourseBookingRequestDTO requestDTO
    ) {
        return courseBookingService.createBooking(requestDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<CourseBookingResponseDTO> getAllBookings() {
        return courseBookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public CourseBookingResponseDTO getBookingById(
            @PathVariable Integer id
    ) {
        return courseBookingService.getBookingById(id);
    }

    @GetMapping("/student/{studId}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<CourseBookingResponseDTO> getBookingsByStudent(
            @PathVariable Integer studId
    ) {
        return courseBookingService.getBookingsByStudent(studId);
    }

    @GetMapping("/intake/{intakeId}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<CourseBookingResponseDTO> getBookingsByIntake(
            @PathVariable Integer intakeId
    ) {
        return courseBookingService.getBookingsByIntake(intakeId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public CourseBookingResponseDTO updateBooking(
            @PathVariable Integer id,
            @RequestBody CourseBookingRequestDTO requestDTO
    ) {
        return courseBookingService.updateBooking(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteBooking(
            @PathVariable Integer id
    ) {

        courseBookingService.deleteBooking(id);

        return "Booking deleted successfully";
    }
}