package com.billing.hostel.repository;

import com.billing.hostel.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

        boolean existsByControlNumber(String controlNumber);

    boolean existsByControlNumberAndIdNot(
            String controlNumber,
            Integer id
    );
}