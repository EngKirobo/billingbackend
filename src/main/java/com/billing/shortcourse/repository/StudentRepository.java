package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByAdmino(String admino);

    boolean existsByAdmino(String admino);
}