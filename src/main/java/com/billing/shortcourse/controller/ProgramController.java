package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.ProgramRequest;
import com.billing.shortcourse.dto.ProgramResponse;
import com.billing.shortcourse.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    // READ (All)
    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<List<ProgramResponse>> getAll() {
        return ResponseEntity.ok(programService.getAll());
    }

    // READ (Single)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ResponseEntity<ProgramResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(programService.getById(id));
    }

    // CREATE
    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ResponseEntity<ProgramResponse> create(@RequestBody ProgramRequest request) {
        return new ResponseEntity<>(programService.save(request), HttpStatus.CREATED);
    }

    // UPDATE
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ResponseEntity<ProgramResponse> update(@PathVariable Integer id, @RequestBody ProgramRequest request) {
        return ResponseEntity.ok(programService.update(id, request));
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        programService.delete(id);
        return ResponseEntity.noContent().build();
    }
}