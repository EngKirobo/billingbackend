package com.billing.hostel.controller;

import com.billing.hostel.dto.HostelRequest;
import com.billing.hostel.dto.HostelResponse;
import com.billing.hostel.service.HostelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HostelController {

    private final HostelService hostelService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<List<HostelResponse>> getAll() {
        return ResponseEntity.ok(hostelService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<HostelResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(hostelService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROOMS_CREATE')")
    public ResponseEntity<HostelResponse> create(@RequestBody HostelRequest request) {
        return ResponseEntity.ok(hostelService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    public ResponseEntity<HostelResponse> update(@PathVariable Long id, 
                                                 @RequestBody HostelRequest request) {
        return ResponseEntity.ok(hostelService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        hostelService.delete(id);
        return ResponseEntity.noContent().build();
    }
}