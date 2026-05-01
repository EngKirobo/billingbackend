package com.billing.hostel.repository;

import com.billing.hostel.entity.HostelBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HostelBookingRepository extends JpaRepository<HostelBooking, Integer> {

    Optional<HostelBooking> findByStudentIdAndAcademicYearAndSemester(
            Integer studentId, String academicYear, String semester
    );
}