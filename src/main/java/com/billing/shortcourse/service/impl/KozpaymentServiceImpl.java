package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.dto.KozpaymentRequestDTO;
import com.billing.shortcourse.dto.KozpaymentResponseDTO;
import com.billing.shortcourse.entity.Kozpayment;
import com.billing.shortcourse.repository.KozpaymentRepository;
import com.billing.shortcourse.service.KozpaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KozpaymentServiceImpl
        implements KozpaymentService {

    private final KozpaymentRepository kozpaymentRepository;

    @Override
    public KozpaymentResponseDTO createPayment(
            KozpaymentRequestDTO requestDTO
    ) {

        boolean exists =
                kozpaymentRepository.existsByControlNumber(
                        requestDTO.getControlNumber()
                );

        if (exists) {
            throw new RuntimeException(
                    "Control number already exists"
            );
        }

        Kozpayment payment = Kozpayment.builder()
                .coursebookingId(
                        requestDTO.getCoursebookingId()
                )
                .controlNumber(
                        requestDTO.getControlNumber()
                )
                .paymentDate(
                        requestDTO.getPaymentDate()
                )
                .status(
                        requestDTO.getStatus()
                )
                .payerName(
                        requestDTO.getPayerName()
                )
                .build();

        Kozpayment saved =
                kozpaymentRepository.save(payment);

        return mapToDTO(saved);
    }

    @Override
    public List<KozpaymentResponseDTO> getAllPayments() {

        return kozpaymentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public KozpaymentResponseDTO getPaymentById(
            Integer id
    ) {

        Kozpayment payment =
                kozpaymentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"
                                ));

        return mapToDTO(payment);
    }

    @Override
    public KozpaymentResponseDTO updatePayment(
            Integer id,
            KozpaymentRequestDTO requestDTO
    ) {

        Kozpayment payment =
                kozpaymentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"
                                ));

        payment.setCoursebookingId(
                requestDTO.getCoursebookingId()
        );

        payment.setControlNumber(
                requestDTO.getControlNumber()
        );

        payment.setPaymentDate(
                requestDTO.getPaymentDate()
        );

        payment.setStatus(
                requestDTO.getStatus()
        );

        payment.setPayerName(
                requestDTO.getPayerName()
        );

        Kozpayment updated =
                kozpaymentRepository.save(payment);

        return mapToDTO(updated);
    }

    @Override
    public void deletePayment(Integer id) {

        Kozpayment payment =
                kozpaymentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment not found"
                                ));

        kozpaymentRepository.delete(payment);
    }

    private KozpaymentResponseDTO mapToDTO(
            Kozpayment payment
    ) {

        return KozpaymentResponseDTO.builder()
                .id(payment.getId())
                .coursebookingId(
                        payment.getCoursebookingId()
                )
                .controlNumber(
                        payment.getControlNumber()
                )
                .paymentDate(
                        payment.getPaymentDate()
                )
                .status(
                        payment.getStatus()
                )
                .payerName(
                        payment.getPayerName()
                )
                .createdAt(
                        payment.getCreatedAt()
                )
                .updatedAt(
                        payment.getUpdatedAt()
                )
                .build();
    }
}