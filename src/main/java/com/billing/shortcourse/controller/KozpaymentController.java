package com.billing.shortcourse.controller;

import com.billing.shortcourse.dto.KozpaymentRequestDTO;
import com.billing.shortcourse.dto.KozpaymentResponseDTO;
import com.billing.shortcourse.service.KozpaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kozpayments")
@RequiredArgsConstructor
@CrossOrigin("*")
public class KozpaymentController {

    private final KozpaymentService kozpaymentService;

    // ================= CREATE =================
    @PostMapping
    @PreAuthorize("hasAuthority('USER_INSERT')")
    public KozpaymentResponseDTO createPayment(
            @RequestBody KozpaymentRequestDTO requestDTO
    ) {
        return kozpaymentService.createPayment(
                requestDTO
        );
    }

    // ================= READ =================
    @GetMapping
    @PreAuthorize("hasAuthority('USER_READ')")
    public List<KozpaymentResponseDTO> getAllPayments() {
        return kozpaymentService.getAllPayments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_READ')")
    public KozpaymentResponseDTO getPaymentById(
            @PathVariable Integer id
    ) {
        return kozpaymentService.getPaymentById(id);
    }

    // ================= UPDATE =================
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_UPDATE')")
    public KozpaymentResponseDTO updatePayment(
            @PathVariable Integer id,
            @RequestBody KozpaymentRequestDTO requestDTO
    ) {

        return kozpaymentService.updatePayment(
                id,
                requestDTO
        );
    }

    // ================= DELETE =================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USER_DELETE')")
    public String deletePayment(
            @PathVariable Integer id
    ) {

        kozpaymentService.deletePayment(id);

        return "Payment deleted successfully";
    }
}