package com.billing.shortcourse.controller;

import com.billing.shortcourse.entity.Kozpaysdetails;
import com.billing.shortcourse.service.KozpaysdetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kozpaysdetails")
@RequiredArgsConstructor
@CrossOrigin("*")
public class KozpaysdetailsController {

    private final KozpaysdetailsService service;

    // ================= GET ALL =================
    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<Kozpaysdetails> getAll() {

        return service.getAll();
    }

    // ================= GET BY ID =================
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public Kozpaysdetails getById(
            @PathVariable Integer id
    ) {

        return service.getById(id);
    }

    // ================= GET BY STUDENT =================
    @GetMapping("/student/{studId}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<Kozpaysdetails> getByStudent(
            @PathVariable Integer studId
    ) {

        return service.getByStudent(studId);
    }

    // ================= GET BY INTAKE =================
    @GetMapping("/intake/{intakeId}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<Kozpaysdetails> getByIntake(
            @PathVariable Integer intakeId
    ) {

        return service.getByIntake(intakeId);
    }

    // ================= GET BY COURSE =================
    @GetMapping("/course/{courseName}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<Kozpaysdetails> getByCourse(
            @PathVariable String courseName
    ) {

        return service.getByCourse(courseName);
    }

    // ================= GET BY CONTROL NUMBER =================
    @GetMapping("/control/{controlnumber}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public Kozpaysdetails getByControlNumber(
            @PathVariable String controlnumber
    ) {

        return service.getByControlNumber(
                controlnumber
        );
    }
}