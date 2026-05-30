package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.ItakeRequestDTO;
import com.billing.shortcourse.dto.ItakeResponseDTO;
import com.billing.shortcourse.service.ItakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itakes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItakeController {

    private final ItakeService itakeService;

    // ==================== CREATE ====================
    @PostMapping
    @PreAuthorize("hasAuthority('USER_CREATE')")
    public ItakeResponseDTO createItake(@RequestBody ItakeRequestDTO requestDTO) {
        return itakeService.createItake(requestDTO);
    }

    // ==================== READ ====================
    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<ItakeResponseDTO> getAllItakes() {
        return itakeService.getAllItakes();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public ItakeResponseDTO getItakeById(@PathVariable Integer id) {
        return itakeService.getItakeById(id);
    }

    // ==================== UPDATE ====================
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public ItakeResponseDTO updateItake(
            @PathVariable Integer id,
            @RequestBody ItakeRequestDTO requestDTO
    ) {
        return itakeService.updateItake(id, requestDTO);
    }

    // ==================== DELETE ====================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deleteItake(@PathVariable Integer id) {
        itakeService.deleteItake(id);
        return "Itake deleted successfully";
    }
}