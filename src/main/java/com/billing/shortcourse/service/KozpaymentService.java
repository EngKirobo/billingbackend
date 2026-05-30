package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.KozpaymentRequestDTO;
import com.billing.shortcourse.dto.KozpaymentResponseDTO;

import java.util.List;

public interface KozpaymentService {

    KozpaymentResponseDTO createPayment(
            KozpaymentRequestDTO requestDTO
    );

    List<KozpaymentResponseDTO> getAllPayments();

    KozpaymentResponseDTO getPaymentById(Integer id);

    KozpaymentResponseDTO updatePayment(
            Integer id,
            KozpaymentRequestDTO requestDTO
    );

    void deletePayment(Integer id);
}