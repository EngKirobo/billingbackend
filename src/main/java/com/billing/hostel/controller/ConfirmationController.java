package com.billing.hostel.controller;

import com.billing.hostel.dto.ConfirmationRequest;
import com.billing.hostel.dto.ConfirmationResponse;
import com.billing.hostel.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/confirmations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ConfirmationController {

    private final ConfirmationService service;

    @PreAuthorize("hasAuthority('CONFIRMATION_INSERT')")
    @PostMapping
    public ConfirmationResponse create(@RequestBody ConfirmationRequest request) {
        return service.create(request);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_READ')")
    @GetMapping
    public List<ConfirmationResponse> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_READ')")
    @GetMapping("/{id}")
    public ConfirmationResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_UPDATE')")
    @PutMapping("/{id}")
    public ConfirmationResponse update(@PathVariable Integer id,
                                       @RequestBody ConfirmationRequest request) {
        return service.update(id, request);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}