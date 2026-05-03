package com.billing.hostel.controller;

import com.billing.hostel.dto.EduLevelRequest;
import com.billing.hostel.entity.EduLevel;
import com.billing.hostel.service.EduLevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/edulevels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class EduLevelController {

    private final EduLevelService eduLevelService;

    // 🔒 CREATE
    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    @PostMapping
    public EduLevel create(@RequestBody EduLevelRequest request) {
        return eduLevelService.create(request);
    }

    // 🔓 GET ALL (no restriction)
    @GetMapping
    public List<EduLevel> getAll() {
        return eduLevelService.getAll();
    }

    // 🔓 GET ONE (no restriction)
    @GetMapping("/{id}")
    public EduLevel getById(@PathVariable Long id) {
        return eduLevelService.getById(id);
    }

    // 🔒 UPDATE
    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    @PutMapping("/{id}")
    public EduLevel update(@PathVariable Long id,@RequestBody EduLevelRequest request) {
        return eduLevelService.update(id, request);
    }

    // 🔒 DELETE
    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        eduLevelService.delete(id);
    }
}