package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.ConfirmRequest;
import com.billing.shortcourse.dto.ConfirmResponse;
import com.billing.shortcourse.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sconfirms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ConfirmController {

    private final ConfirmService service;

    @PreAuthorize("hasAuthority('CONFIRMATION_INSERT')")
    @PostMapping
    public ConfirmResponse create(@RequestBody ConfirmRequest request) {
        return service.create(request);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_READ')")
    @GetMapping
    public List<ConfirmResponse> getAll() {
        return service.getAll();
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_READ')")
    @GetMapping("/{id}")
    public ConfirmResponse getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_UPDATE')")
    @PutMapping("/{id}")
    public ConfirmResponse update(@PathVariable Integer id,
                                       @RequestBody ConfirmRequest request) {
        return service.update(id, request);
    }

    @PreAuthorize("hasAuthority('CONFIRMATION_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}