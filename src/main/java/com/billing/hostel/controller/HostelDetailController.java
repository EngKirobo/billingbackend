package com.billing.hostel.controller;

import com.billing.hostel.dto.HostelDetailRequest;
import com.billing.hostel.dto.HostelDetailResponse;
import com.billing.hostel.service.HostelDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostel-details")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HostelDetailController {

    private final HostelDetailService hostelDetailService;

    @GetMapping
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_READ')")
    public ResponseEntity<List<HostelDetailResponse>> getAll() {
        return ResponseEntity.ok(hostelDetailService.getAll());
    }

    @GetMapping("/hostel/{hostelId}")
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_READ')")
    public ResponseEntity<List<HostelDetailResponse>> getByHostelId(@PathVariable Long hostelId) {
        return ResponseEntity.ok(hostelDetailService.getByHostelId(hostelId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_READ')")
    public ResponseEntity<HostelDetailResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelDetailService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_CREATE')")
    public ResponseEntity<HostelDetailResponse> create(@RequestBody HostelDetailRequest request) {
        return ResponseEntity.ok(hostelDetailService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_UPDATE')")
    public ResponseEntity<HostelDetailResponse> update(@PathVariable Long id, 
                                                       @RequestBody HostelDetailRequest request) {
        return ResponseEntity.ok(hostelDetailService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('HOSTEL_DETAIL_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostelDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}