package com.billing.shortcourse.service;

import com.billing.shortcourse.dto.ItakeRequestDTO;
import com.billing.shortcourse.dto.ItakeResponseDTO;

import java.util.List;

public interface ItakeService {

    ItakeResponseDTO createItake(ItakeRequestDTO requestDTO);

    List<ItakeResponseDTO> getAllItakes();

    ItakeResponseDTO getItakeById(Integer id);

    ItakeResponseDTO updateItake(Integer id, ItakeRequestDTO requestDTO);

    void deleteItake(Integer id);
}