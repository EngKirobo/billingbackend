package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.KozstudentRequestDTO;
import com.billing.shortcourse.dto.KozstudentResponseDTO;
import com.billing.shortcourse.service.KozstudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kozstudents")
@RequiredArgsConstructor
@CrossOrigin("*")
public class KozstudentController {

    private final KozstudentService service;

    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<KozstudentResponseDTO> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public KozstudentResponseDTO getById(
            @PathVariable Integer id
    ) {

        return service.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER_INSERT')")
    public KozstudentResponseDTO create(
            @RequestBody KozstudentRequestDTO dto
    ) {

        return service.create(dto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public KozstudentResponseDTO update(
            @PathVariable Integer id,
            @RequestBody KozstudentRequestDTO dto
    ) {

        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public void delete(
            @PathVariable Integer id
    ) {

        service.delete(id);
    }
}