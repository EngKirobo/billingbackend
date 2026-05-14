package com.billing.hostel.controller;

import com.billing.hostel.dto.HostelDetailRequest;
import com.billing.hostel.dto.HostelDetailResponse;
import com.billing.hostel.service.HostelDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosteldetails")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HostelDetailController {

    private final HostelDetailService service;

    // CREATE
    @PostMapping
    @PreAuthorize("hasAuthority('ROOMS_CREATE')")
    public ResponseEntity<HostelDetailResponse> create(@RequestBody HostelDetailRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    // READ ALL
    @GetMapping
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<List<HostelDetailResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // READ BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<HostelDetailResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<HostelDetailResponse> update(
            @PathVariable Long id,
            @RequestBody HostelDetailRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}