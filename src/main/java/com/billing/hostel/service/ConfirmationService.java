package com.billing.hostel.service;

import com.billing.hostel.dto.ConfirmationRequest;
import com.billing.hostel.dto.ConfirmationResponse;

import java.util.List;

public interface ConfirmationService {

    ConfirmationResponse create(ConfirmationRequest request);

    List<ConfirmationResponse> getAll();

    ConfirmationResponse getById(Integer id);

    ConfirmationResponse update(Integer id, ConfirmationRequest request);

    void delete(Integer id);
}