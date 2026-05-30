package com.billing.shortcourse.repository;

import com.billing.shortcourse.entity.Kozpaysdetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KozpaysdetailsRepository
        extends JpaRepository<Kozpaysdetails, Integer> {

    List<Kozpaysdetails> findByStudId(Integer studId);

    List<Kozpaysdetails> findByIntakeId(Integer intakeId);

    List<Kozpaysdetails> findByCourseName(String courseName);

    Kozpaysdetails findByControlnumber(String controlnumber);
}