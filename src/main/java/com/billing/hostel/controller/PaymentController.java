package com.billing.hostel.controller;

import com.billing.hostel.dto.PaymentRequestDTO;
import com.billing.hostel.dto.PaymentResponseDTO;
import com.billing.hostel.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasAuthority('ROOMS_CREATE')")
    @PostMapping
    public PaymentResponseDTO create(@RequestBody PaymentRequestDTO dto) {
        return paymentService.createPayment(dto);
    }

    @PreAuthorize("hasAuthority('ROOMS_READ')")
    @GetMapping
    public List<PaymentResponseDTO> getAll() {
        return paymentService.getAllPayments();
    }

    @PreAuthorize("hasAuthority('ROOMS_READ')")
    @GetMapping("/{id}")
    public PaymentResponseDTO getById(@PathVariable Integer id) {
        return paymentService.getPaymentById(id);
    }

    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    @PutMapping("/{id}")
    public PaymentResponseDTO update(
            @PathVariable Integer id,
            @RequestBody PaymentRequestDTO dto
    ) {
        return paymentService.updatePayment(id, dto);
    }

    @PreAuthorize("hasAuthority('ROOMS_DELETE')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        paymentService.deletePayment(id);
    }
}