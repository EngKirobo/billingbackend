package com.billing.hostel.service.impl;

import com.billing.hostel.dto.ConfirmationRequest;
import com.billing.hostel.dto.ConfirmationResponse;
import com.billing.hostel.entity.Confirmation;
import com.billing.hostel.repository.ConfirmationRepository;
import com.billing.hostel.service.ConfirmationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfirmationServiceImpl implements ConfirmationService {

    private final ConfirmationRepository repository;

    // @Override
    // public ConfirmationResponse create(ConfirmationRequest request) {

    //     Confirmation confirmation = mapToEntity(request);

    //     return mapToDTO(repository.save(confirmation));
    // }

        @Override
    public ConfirmationResponse create(ConfirmationRequest request) {

        // ✅ CHECK DUPLICATE CONTROL NUMBER
        if (request.getControlNumber() != null &&
            repository.findByControlNumber(request.getControlNumber()).isPresent()) {

            throw new RuntimeException("Control number already exists");
        }

        Confirmation confirmation = mapToEntity(request);

        return mapToDTO(repository.save(confirmation));
    }

    @Override
    public List<ConfirmationResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ConfirmationResponse getById(Integer id) {
        Confirmation confirmation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confirmation not found"));

        return mapToDTO(confirmation);
    }

    @Override
    public ConfirmationResponse update(Integer id, ConfirmationRequest request) {

        Confirmation confirmation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confirmation not found"));

        confirmation.setBillId(request.getBillId());
        confirmation.setFullName(request.getFullName());
        confirmation.setPayRefId(request.getPayRefId());
        confirmation.setControlNumber(request.getControlNumber());
        confirmation.setPdates(request.getPdates());
        confirmation.setAmountPaid(request.getAmountPaid());
        confirmation.setPayMethod(request.getPayMethod());
        confirmation.setReceiptNo(request.getReceiptNo());

        return mapToDTO(repository.save(confirmation));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // ===== MAPPERS =====

    private Confirmation mapToEntity(ConfirmationRequest r) {
        return Confirmation.builder()
                .billId(r.getBillId())
                .fullName(r.getFullName())
                .payRefId(r.getPayRefId())
                .controlNumber(r.getControlNumber())
                .pdates(r.getPdates())
                .amountPaid(r.getAmountPaid())
                .payMethod(r.getPayMethod())
                .receiptNo(r.getReceiptNo())
                .build();
    }

    private ConfirmationResponse mapToDTO(Confirmation c) {
        return ConfirmationResponse.builder()
                .payid(c.getPayid())
                .billId(c.getBillId())
                .fullName(c.getFullName())
                .payRefId(c.getPayRefId())
                .controlNumber(c.getControlNumber())
                .pdates(c.getPdates())
                .amountPaid(c.getAmountPaid())
                .payMethod(c.getPayMethod())
                .receiptNo(c.getReceiptNo())
                .build();
    }
}