package com.billing.hostel.repository;

import com.billing.hostel.entity.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Integer> {

    Optional<Confirmation> findByControlNumber(String controlNumber);
}