package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Itake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItakeRepository extends JpaRepository<Itake, Integer> {
}