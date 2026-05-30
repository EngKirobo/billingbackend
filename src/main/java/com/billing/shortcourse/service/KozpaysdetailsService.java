package com.billing.shortcourse.service;

import com.billing.shortcourse.entity.Kozpaysdetails;

import java.util.List;

public interface KozpaysdetailsService {

    List<Kozpaysdetails> getAll();

    Kozpaysdetails getById(Integer id);

    List<Kozpaysdetails> getByStudent(Integer studId);

    List<Kozpaysdetails> getByIntake(Integer intakeId);

    List<Kozpaysdetails> getByCourse(String courseName);

    Kozpaysdetails getByControlNumber(String controlnumber);
}