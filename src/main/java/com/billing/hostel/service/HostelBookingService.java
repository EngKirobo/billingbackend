package com.billing.hostel.service;

import com.billing.hostel.dto.HostelBookingRequest;
import com.billing.hostel.dto.HostelBookingResponse;
import com.billing.hostel.entity.HostelBooking;
import com.billing.hostel.repository.HostelBookingRepository;
import com.billing.hostel.repository.RoomRepository;
import com.billing.shortcourse.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HostelBookingService {

    private final HostelBookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    public boolean existsBooking(Integer studentId, String academicYear, String semester) {
        return bookingRepository.existsByStudentIdAndAcademicYearAndSemester(
                studentId, academicYear, semester
        );
    }

    // ✅ CREATE
//     public HostelBookingResponse create(HostelBookingRequest request) {

//         // Validate Room exists
//         roomRepository.findById(request.getRoomId())
//                 .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomId()));

//         // Validate Student exists
//         studentRepository.findById(request.getStudentId())
//                 .orElseThrow(() -> new RuntimeException("Student not found with ID: " + request.getStudentId()));

//         HostelBooking booking = HostelBooking.builder()
//                 .roomId(request.getRoomId())
//                 .studentId(request.getStudentId())
//                 .academicYear(request.getAcademicYear())
//                 .semester(request.getSemester())
//                 .verified(request.getVerified() != null ? request.getVerified() : false)
//                 .allowed(request.getAllowed() != null ? request.getAllowed() : false)
//                 .build();

//         HostelBooking savedBooking = bookingRepository.save(booking);
//         return mapToResponse(savedBooking);
//     }

public HostelBookingResponse create(HostelBookingRequest request) {

    // ✅ Check duplicate based on 3 fields only
    boolean exists = existsBooking(
        request.getStudentId(),
        request.getAcademicYear(),
        request.getSemester()
    );

//     if (exists) {
//         throw new RuntimeException(
//             "This student already has a booking for this academic year and semester"
//         );
//     }

if (exists) {
    throw new ResponseStatusException(
        HttpStatus.CONFLICT,
        "Student already booked for this academic year and semester"
    );
}

    // Validate Room
    roomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomId()));

    // Validate Student
    studentRepository.findById(request.getStudentId())
            .orElseThrow(() -> new RuntimeException("Student not found with ID: " + request.getStudentId()));

    HostelBooking booking = HostelBooking.builder()
            .roomId(request.getRoomId())
            .studentId(request.getStudentId())
            .academicYear(request.getAcademicYear())
            .semester(request.getSemester())
            .verified(request.getVerified() != null ? request.getVerified() : false)
            .allowed(request.getAllowed() != null ? request.getAllowed() : false)
            .build();

    return mapToResponse(bookingRepository.save(booking));
}

    // ✅ READ ALL
    @Transactional(readOnly = true)
    public List<HostelBookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ READ ONE
    @Transactional(readOnly = true)
    public HostelBookingResponse getById(Integer id) {
        HostelBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));
        return mapToResponse(booking);
    }

    // ✅ UPDATE
    public HostelBookingResponse update(Integer id, HostelBookingRequest request) {

        HostelBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + id));

        // Validate Room
        roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found with ID: " + request.getRoomId()));

        // Validate Student
        studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + request.getStudentId()));

        // Update fields
        booking.setRoomId(request.getRoomId());
        booking.setStudentId(request.getStudentId());
        booking.setAcademicYear(request.getAcademicYear());
        booking.setSemester(request.getSemester());
        booking.setVerified(request.getVerified() != null ? request.getVerified() : booking.getVerified());
        booking.setAllowed(request.getAllowed() != null ? request.getAllowed() : booking.getAllowed());

        HostelBooking updatedBooking = bookingRepository.save(booking);
        return mapToResponse(updatedBooking);
    }

    // ✅ DELETE
    public void delete(Integer id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }

    // ✅ MAPPER
    private HostelBookingResponse mapToResponse(HostelBooking b) {
        return HostelBookingResponse.builder()
                .id(b.getId())
                .roomId(b.getRoomId())
                .studentId(b.getStudentId())
                .academicYear(b.getAcademicYear())
                .semester(b.getSemester())
                .verified(b.getVerified())
                .allowed(b.getAllowed())
                .createdAt(b.getCreatedAt())
                .updatedAt(b.getUpdatedAt())
                .build();
    }
}