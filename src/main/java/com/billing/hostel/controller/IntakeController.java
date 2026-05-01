package com.billing.hostel.controller;

import com.billing.hostel.dto.IntakeRequest;
import com.billing.hostel.entity.Intake;
import com.billing.hostel.service.IntakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intakes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IntakeController {

    private final IntakeService intakeService;

    // CREATE
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PostMapping
    public Intake create(@RequestBody IntakeRequest request) {
        return intakeService.create(request);
    }

    // GET ALL
    @GetMapping
    public List<Intake> getAll() {
        return intakeService.getAll();
    }

    // GET ONE
    @GetMapping("/{id}")
    public Intake getById(@PathVariable Long id) {
        return intakeService.getById(id);
    }

    // UPDATE
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @PutMapping("/{id}")
    public Intake update(@PathVariable Long id, @RequestBody IntakeRequest request) {
        return intakeService.update(id, request);
    }

    // DELETE
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        intakeService.delete(id);
    }
}