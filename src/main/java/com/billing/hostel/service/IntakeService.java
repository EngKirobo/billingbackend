package com.billing.hostel.service;

import com.billing.hostel.dto.IntakeRequest;
import com.billing.hostel.entity.Intake;
import com.billing.hostel.repository.IntakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IntakeService {

    private final IntakeRepository intakeRepository;

    // CREATE
    public Intake create(IntakeRequest request) {
        Intake intake = Intake.builder()
                .intake(request.getIntake())
                .build();

        return intakeRepository.save(intake);
    }

    // READ ALL
    public List<Intake> getAll() {
        return intakeRepository.findAll();
    }

    // READ ONE
    public Intake getById(Long id) {
        return intakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Intake not found"));
    }

    // UPDATE
    public Intake update(Long id, IntakeRequest request) {
        Intake intake = getById(id);
        intake.setIntake(request.getIntake());
        return intakeRepository.save(intake);
    }

    // DELETE
    public void delete(Long id) {
        intakeRepository.deleteById(id);
    }
}