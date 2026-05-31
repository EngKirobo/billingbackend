package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Kozstudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KozstudentRepository
        extends JpaRepository<Kozstudent, Integer> {
}