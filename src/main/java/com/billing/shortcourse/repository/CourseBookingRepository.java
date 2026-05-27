package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.CourseBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseBookingRepository extends JpaRepository<CourseBooking, Integer> {

    List<CourseBooking> findByIntakeId(Integer intakeId);

    List<CourseBooking> findByStudId(Integer studId);

    boolean existsByStudIdAndIntakeId(Integer studId, Integer intakeId);
}