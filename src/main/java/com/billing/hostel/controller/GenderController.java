package com.billing.hostel.controller;

import com.billing.hostel.dto.GenderRequest;
import com.billing.hostel.dto.GenderResponse;
import com.billing.hostel.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genders")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class GenderController {

    private final GenderService service;

    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<GenderResponse> create(@RequestBody GenderRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<List<GenderResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<GenderResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<GenderResponse> update(
            @PathVariable Integer id,
            @RequestBody GenderRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}