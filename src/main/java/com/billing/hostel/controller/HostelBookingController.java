package com.billing.hostel.controller;

import com.billing.hostel.dto.HostelBookingRequest;
import com.billing.hostel.dto.HostelBookingResponse;
import com.billing.hostel.service.HostelBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hostelbookings")
@EnableMethodSecurity  // ✅ VERY IMPORTANT
@RequiredArgsConstructor
public class HostelBookingController {

    private final HostelBookingService bookingService;

    @PreAuthorize("hasAuthority('ROOM_CREATE')")
    @PostMapping
    public HostelBookingResponse create(@RequestBody HostelBookingRequest request) {
        return bookingService.create(request);
    }

    @PreAuthorize("hasAuthority('ROOMS_READ')")
    @GetMapping
    public List<HostelBookingResponse> getAll() {
        return bookingService.getAll();
    }

    @PreAuthorize("hasAuthority('ROOMS_READ')")
    @GetMapping("/{id}")
    public HostelBookingResponse getById(@PathVariable Integer id) {
        return bookingService.getById(id);
    }

    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    @PutMapping("/{id}")
    public HostelBookingResponse update(@PathVariable Integer id,
                                        @RequestBody HostelBookingRequest request) {
        return bookingService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ROOMS_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        bookingService.delete(id);
    }
}