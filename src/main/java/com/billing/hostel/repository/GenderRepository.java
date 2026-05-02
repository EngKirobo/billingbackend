package com.billing.hostel.repository;

import com.billing.hostel.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {
}