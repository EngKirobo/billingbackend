package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.entity.Kozpaysdetails;
import com.billing.shortcourse.repository.KozpaysdetailsRepository;
import com.billing.shortcourse.service.KozpaysdetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KozpaysdetailsServiceImpl
        implements KozpaysdetailsService {

    private final KozpaysdetailsRepository repository;

    @Override
    public List<Kozpaysdetails> getAll() {

        return repository.findAll();
    }

    @Override
    public Kozpaysdetails getById(Integer id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Record not found"
                        ));
    }

    @Override
    public List<Kozpaysdetails> getByStudent(
            Integer studId
    ) {

        return repository.findByStudId(studId);
    }

    @Override
    public List<Kozpaysdetails> getByIntake(
            Integer intakeId
    ) {

        return repository.findByIntakeId(intakeId);
    }

    @Override
    public List<Kozpaysdetails> getByCourse(
            String courseName
    ) {

        return repository.findByCourseName(courseName);
    }

    @Override
    public Kozpaysdetails getByControlNumber(
            String controlnumber
    ) {

        return repository.findByControlnumber(
                controlnumber
        );
    }
}