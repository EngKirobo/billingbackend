package com.billing.hostel.repository;

import com.billing.hostel.entity.Intake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntakeRepository extends JpaRepository<Intake, Long> {
}