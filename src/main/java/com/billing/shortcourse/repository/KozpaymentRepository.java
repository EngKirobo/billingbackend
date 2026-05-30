package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Kozpayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KozpaymentRepository
        extends JpaRepository<Kozpayment, Integer> {

    boolean existsByControlNumber(String controlNumber);
}