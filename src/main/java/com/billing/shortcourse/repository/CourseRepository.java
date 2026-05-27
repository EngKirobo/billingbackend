package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    // Custom queries (optional)
    List<Course> findByDeptId(Integer deptId);

    List<Course> findByPriceLessThanEqual(BigDecimal price);
}