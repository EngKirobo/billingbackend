package com.billing.shortcourse.service.impl;

import com.billing.shortcourse.dto.ItakeRequestDTO;
import com.billing.shortcourse.dto.ItakeResponseDTO;
import com.billing.shortcourse.entity.Itake;
import com.billing.shortcourse.repository.ItakeRepository;
import com.billing.shortcourse.service.ItakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItakeServiceImpl implements ItakeService {

    private final ItakeRepository itakeRepository;

    @Override
    public ItakeResponseDTO createItake(ItakeRequestDTO requestDTO) {

        Itake itake = Itake.builder()
                .courseId(requestDTO.getCourseId())
                .build();

        Itake saved = itakeRepository.save(itake);

        return mapToDTO(saved);
    }

    @Override
    public List<ItakeResponseDTO> getAllItakes() {

        return itakeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ItakeResponseDTO getItakeById(Integer id) {

        Itake itake = itakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itake not found"));

        return mapToDTO(itake);
    }

    @Override
    public ItakeResponseDTO updateItake(Integer id, ItakeRequestDTO requestDTO) {

        Itake itake = itakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itake not found"));

        itake.setCourseId(requestDTO.getCourseId());

        Itake updated = itakeRepository.save(itake);

        return mapToDTO(updated);
    }

    @Override
    public void deleteItake(Integer id) {

        Itake itake = itakeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Itake not found"));

        itakeRepository.delete(itake);
    }

    private ItakeResponseDTO mapToDTO(Itake itake) {

        return ItakeResponseDTO.builder()
                .id(itake.getId())
                .courseId(itake.getCourseId())
                .createdAt(itake.getCreatedAt())
                .updatedAt(itake.getUpdatedAt())
                .build();
    }
}