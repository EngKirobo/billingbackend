package com.billing.hostel.service;

import com.billing.hostel.dto.PaymentRequestDTO;
import com.billing.hostel.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO createPayment(PaymentRequestDTO dto);

    List<PaymentResponseDTO> getAllPayments();

    PaymentResponseDTO getPaymentById(Integer id);

    PaymentResponseDTO updatePayment(Integer id, PaymentRequestDTO dto);

    void deletePayment(Integer id);
}