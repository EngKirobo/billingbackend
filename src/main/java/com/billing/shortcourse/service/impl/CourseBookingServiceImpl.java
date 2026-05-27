package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.dto.CourseBookingRequestDTO;
import com.billing.shortcourse.dto.CourseBookingResponseDTO;
import com.billing.shortcourse.entity.CourseBooking;
import com.billing.shortcourse.repository.CourseBookingRepository;
import com.billing.shortcourse.service.CourseBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseBookingServiceImpl implements CourseBookingService {

    private final CourseBookingRepository courseBookingRepository;

    @Override
    public CourseBookingResponseDTO createBooking(
            CourseBookingRequestDTO requestDTO
    ) {

        boolean exists = courseBookingRepository
                .existsByStudIdAndIntakeId(
                        requestDTO.getStudId(),
                        requestDTO.getIntakeId()
                );

        if (exists) {
            throw new RuntimeException(
                    "Student already booked for this intake"
            );
        }

        CourseBooking booking = CourseBooking.builder()
                .intakeId(requestDTO.getIntakeId())
                .studId(requestDTO.getStudId())
                .verified(requestDTO.getVerified())
                .allowed(requestDTO.getAllowed())
                .ctn(requestDTO.getCtn())
                .build();

        CourseBooking saved = courseBookingRepository.save(booking);

        return mapToDTO(saved);
    }

    @Override
    public List<CourseBookingResponseDTO> getAllBookings() {

        return courseBookingRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CourseBookingResponseDTO getBookingById(Integer id) {

        CourseBooking booking = courseBookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        return mapToDTO(booking);
    }

    @Override
    public List<CourseBookingResponseDTO> getBookingsByStudent(
            Integer studId
    ) {

        return courseBookingRepository.findByStudId(studId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public List<CourseBookingResponseDTO> getBookingsByIntake(
            Integer intakeId
    ) {

        return courseBookingRepository.findByIntakeId(intakeId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public CourseBookingResponseDTO updateBooking(
            Integer id,
            CourseBookingRequestDTO requestDTO
    ) {

        CourseBooking booking = courseBookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        booking.setIntakeId(requestDTO.getIntakeId());
        booking.setStudId(requestDTO.getStudId());
        booking.setVerified(requestDTO.getVerified());
        booking.setAllowed(requestDTO.getAllowed());
        booking.setCtn(requestDTO.getCtn());

        CourseBooking updated = courseBookingRepository.save(booking);

        return mapToDTO(updated);
    }

    @Override
    public void deleteBooking(Integer id) {

        CourseBooking booking = courseBookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Booking not found"));

        courseBookingRepository.delete(booking);
    }

    private CourseBookingResponseDTO mapToDTO(
            CourseBooking booking
    ) {

        return CourseBookingResponseDTO.builder()
                .id(booking.getId())
                .intakeId(booking.getIntakeId())
                .studId(booking.getStudId())
                .verified(booking.getVerified())
                .allowed(booking.getAllowed())
                .ctn(booking.getCtn())
                .createdAt(booking.getCreatedAt())
                .updatedAt(booking.getUpdatedAt())
                .build();
    }
}