package com.billing.hostel.service.impl;

import com.billing.hostel.dto.HostelDetailRequest;
import com.billing.hostel.dto.HostelDetailResponse;
import com.billing.hostel.entity.HostelDetail;
import com.billing.hostel.repository.HostelDetailRepository;
import com.billing.hostel.service.HostelDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HostelDetailServiceImpl implements HostelDetailService {

    private final HostelDetailRepository repository;

    private HostelDetailResponse map(HostelDetail d) {
        return HostelDetailResponse.builder()
                .id(d.getId())
                .hostelId(d.getHostelId())
                .name(d.getName())
                .levelId(d.getLevelId())
                .intakeId(d.getIntakeId())
                .genderId(d.getGenderId())
                .createdAt(d.getCreatedAt())
                .updatedAt(d.getUpdatedAt())
                .build();
    }

    @Override
    public HostelDetailResponse create(HostelDetailRequest request) {

        HostelDetail d = new HostelDetail();
        d.setHostelId(request.getHostelId());
        d.setName(request.getName());
        d.setLevelId(request.getLevelId());
        d.setIntakeId(request.getIntakeId());
        d.setGenderId(request.getGenderId());

        return map(repository.save(d));
    }

    @Override
    public HostelDetailResponse update(Long id, HostelDetailRequest request) {

        HostelDetail d = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        d.setHostelId(request.getHostelId());
        d.setName(request.getName());
        d.setLevelId(request.getLevelId());
        d.setIntakeId(request.getIntakeId());
        d.setGenderId(request.getGenderId());

        return map(repository.save(d));
    }

    @Override
    public HostelDetailResponse getById(Long id) {
        return repository.findById(id)
                .map(this::map)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public List<HostelDetailResponse> getAll() {
        return repository.findAll().stream().map(this::map).toList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}