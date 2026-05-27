package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.ItakeRequestDTO;
import com.billing.shortcourse.dto.ItakeResponseDTO;
import com.billing.shortcourse.service.ItakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itakes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItakeController {

    private final ItakeService itakeService;

    @PostMapping
    public ItakeResponseDTO createItake(
            @RequestBody ItakeRequestDTO requestDTO
    ) {
        return itakeService.createItake(requestDTO);
    }

    @GetMapping
    public List<ItakeResponseDTO> getAllItakes() {
        return itakeService.getAllItakes();
    }

    @GetMapping("/{id}")
    public ItakeResponseDTO getItakeById(
            @PathVariable Integer id
    ) {
        return itakeService.getItakeById(id);
    }

    @PutMapping("/{id}")
    public ItakeResponseDTO updateItake(
            @PathVariable Integer id,
            @RequestBody ItakeRequestDTO requestDTO
    ) {
        return itakeService.updateItake(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteItake(
            @PathVariable Integer id
    ) {

        itakeService.deleteItake(id);

        return "Itake deleted successfully";
    }
}