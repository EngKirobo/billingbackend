package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.ConfirmRequest;
import com.billing.shortcourse.dto.ConfirmResponse;

import java.util.List;

public interface ConfirmService {

    ConfirmResponse create(ConfirmRequest request);

    List<ConfirmResponse> getAll();

    ConfirmResponse getById(Integer id);

    ConfirmResponse update(Integer id, ConfirmRequest request);

    void delete(Integer id);
}