package com.billing.hostel.service.impl;

import com.billing.hostel.dto.GenderRequest;
import com.billing.hostel.dto.GenderResponse;
import com.billing.hostel.entity.Gender;
import com.billing.hostel.repository.GenderRepository;
import com.billing.hostel.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository repository;

    @Override
    public GenderResponse create(GenderRequest request) {
        Gender gender = Gender.builder()
                .gender(request.getGender())
                .build();

        return mapToResponse(repository.save(gender));
    }

    @Override
    public List<GenderResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public GenderResponse getById(Integer id) {
        Gender gender = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gender not found"));

        return mapToResponse(gender);
    }

    @Override
    public GenderResponse update(Integer id, GenderRequest request) {
        Gender gender = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Gender not found"));

        gender.setGender(request.getGender());

        return mapToResponse(repository.save(gender));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private GenderResponse mapToResponse(Gender gender) {
        return GenderResponse.builder()
                .id(gender.getId())
                .gender(gender.getGender())
                .createdAt(gender.getCreatedAt())
                .updatedAt(gender.getUpdatedAt())
                .build();
    }
}