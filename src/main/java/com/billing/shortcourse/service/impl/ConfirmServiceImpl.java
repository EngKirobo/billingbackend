package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.dto.ConfirmRequest;
import com.billing.shortcourse.dto.ConfirmResponse;
import com.billing.shortcourse.entity.Confirm;
import com.billing.shortcourse.repository.ConfirmRepository;
import com.billing.shortcourse.service.ConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfirmServiceImpl implements ConfirmService {

    private final ConfirmRepository repository;



        @Override
    public ConfirmResponse create(ConfirmRequest request) {

        // ✅ CHECK DUPLICATE CONTROL NUMBER
        if (request.getControlNumber() != null &&
            repository.findByControlNumber(request.getControlNumber()).isPresent()) {

            throw new RuntimeException("Control number already exists");
        }

        Confirm confirmation = mapToEntity(request);

        return mapToDTO(repository.save(confirmation));
    }

    @Override
    public List<ConfirmResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ConfirmResponse getById(Integer id) {
        Confirm confirmation = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Confirmation not found"));

        return mapToDTO(confirmation);
    }

    @Override
    public ConfirmResponse update(Integer id, ConfirmRequest request) {

        Confirm confirmation = repository.findById(id)
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

    private Confirm mapToEntity(ConfirmRequest r) {
        return Confirm.builder()
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

    private ConfirmResponse mapToDTO(Confirm c) {
        return ConfirmResponse.builder()
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