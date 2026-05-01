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

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class HostelBookingService {

    private final HostelBookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final StudentRepository studentRepository;

    // ✅ CREATE
    public HostelBookingResponse create(HostelBookingRequest request) {

        bookingRepository.findByStudentIdAndAcademicYearAndSemester(
                request.getStudentId(),
                request.getAcademicYear(),
                request.getSemester()
        ).ifPresent(b -> {
            throw new RuntimeException("Booking already exists for this student/year/semester");
        });

        // ✅ Validate Room exists (NO relationship used)
        roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // ✅ Validate Student exists (external DB check only)
        studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        HostelBooking booking = HostelBooking.builder()
                .roomId(request.getRoomId()) // ✅ FIXED
                .studentId(request.getStudentId())
                .academicYear(request.getAcademicYear())
                .semester(request.getSemester())
                .verified(request.getVerified())
                .allowed(request.getAllowed())
                .build();

        return mapToResponse(bookingRepository.save(booking));
    }

    // ✅ READ ALL
    public List<HostelBookingResponse> getAll() {
        return bookingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ✅ READ ONE
    public HostelBookingResponse getById(Integer id) {
        HostelBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToResponse(booking);
    }

    // ✅ UPDATE
    public HostelBookingResponse update(Integer id, HostelBookingRequest request) {

        HostelBooking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // validate room
        roomRepository.findById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        // validate student
        studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        booking.setRoomId(request.getRoomId());   // ✅ FIXED
        booking.setStudentId(request.getStudentId());
        booking.setAcademicYear(request.getAcademicYear());
        booking.setSemester(request.getSemester());
        booking.setVerified(request.getVerified());
        booking.setAllowed(request.getAllowed());

        return mapToResponse(bookingRepository.save(booking));
    }

    // ✅ DELETE
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    // ✅ MAPPER
    private HostelBookingResponse mapToResponse(HostelBooking b) {
        return HostelBookingResponse.builder()
                .id(b.getId())
                .roomId(b.getRoomId())      // ✅ FIXED
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