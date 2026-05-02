package com.billing.hostel.service;

import com.billing.hostel.dto.GenderRequest;
import com.billing.hostel.dto.GenderResponse;

import java.util.List;

public interface GenderService {

    GenderResponse create(GenderRequest request);

    List<GenderResponse> getAll();

    GenderResponse getById(Integer id);

    GenderResponse update(Integer id, GenderRequest request);

    void delete(Integer id);
}