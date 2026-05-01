package com.billing.hostel.service.impl;

import com.billing.hostel.dto.PaymentRequestDTO;
import com.billing.hostel.dto.PaymentResponseDTO;
import com.billing.hostel.entity.Payment;
import com.billing.hostel.repository.PaymentRepository;
import com.billing.hostel.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {

        Payment payment = Payment.builder()
                .hostelbookingId(dto.getHostelbookingId())
                .controlNumber(dto.getControlNumber())
                .status(dto.getStatus())
                .build();

        return mapToDTO(paymentRepository.save(payment));
    }

    @Override
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public PaymentResponseDTO getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        return mapToDTO(payment);
    }

    @Override
    public PaymentResponseDTO updatePayment(Integer id, PaymentRequestDTO dto) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        payment.setHostelbookingId(dto.getHostelbookingId());
        payment.setControlNumber(dto.getControlNumber());
        payment.setStatus(dto.getStatus());

        return mapToDTO(paymentRepository.save(payment));
    }

    @Override
    public void deletePayment(Integer id) {
        paymentRepository.deleteById(id);
    }

    private PaymentResponseDTO mapToDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .id(payment.getId())
                .hostelbookingId(payment.getHostelbookingId())
                .controlNumber(payment.getControlNumber())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus())
                .build();
    }
}