package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Confirm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmRepository extends JpaRepository<Confirm, Integer> {

    Optional<Confirm> findByControlNumber(String controlNumber);
}