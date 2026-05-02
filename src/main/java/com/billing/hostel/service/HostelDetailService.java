package com.billing.hostel.service;

import com.billing.hostel.dto.HostelDetailRequest;
import com.billing.hostel.dto.HostelDetailResponse;

import java.util.List;

public interface HostelDetailService {

    HostelDetailResponse create(HostelDetailRequest request);

    HostelDetailResponse update(Long id, HostelDetailRequest request);

    HostelDetailResponse getById(Long id);

    List<HostelDetailResponse> getAll();

    void delete(Long id);
}