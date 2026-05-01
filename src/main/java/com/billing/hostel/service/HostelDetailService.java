package com.billing.hostel.service;

import com.billing.hostel.dto.HostelDetailRequest;
import com.billing.hostel.dto.HostelDetailResponse;
import com.billing.hostel.entity.Hostel;
import com.billing.hostel.entity.HostelDetail;
import com.billing.hostel.repository.HostelDetailRepository;
import com.billing.hostel.repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostelDetailService {

    private final HostelDetailRepository hostelDetailRepository;
    private final HostelRepository hostelRepository;

    public List<HostelDetailResponse> getAll() {
        return hostelDetailRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public List<HostelDetailResponse> getByHostelId(Long hostelId) {
        return hostelDetailRepository.findByHostelIdWithHostel(hostelId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public HostelDetailResponse getById(Long id) {
        HostelDetail detail = hostelDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel detail not found with id: " + id));
        return convertToResponse(detail);
    }

    @Transactional
    public HostelDetailResponse create(HostelDetailRequest request) {
        Hostel hostel = hostelRepository.findById(request.getHostelId())
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + request.getHostelId()));

        HostelDetail detail = new HostelDetail();
        detail.setHostel(hostel);
        detail.setName(request.getName());
        detail.setLevelId(request.getLevelId());
        detail.setIntakeId(request.getIntakeId());
        detail.setGenderId(request.getGenderId());

        HostelDetail saved = hostelDetailRepository.save(detail);
        return convertToResponse(saved);
    }

    @Transactional
    public HostelDetailResponse update(Long id, HostelDetailRequest request) {
        HostelDetail detail = hostelDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel detail not found with id: " + id));

        Hostel hostel = hostelRepository.findById(request.getHostelId())
                .orElseThrow(() -> new RuntimeException("Hostel not found"));

        detail.setHostel(hostel);
        detail.setName(request.getName());
        detail.setLevelId(request.getLevelId());
        detail.setIntakeId(request.getIntakeId());
        detail.setGenderId(request.getGenderId());

        HostelDetail updated = hostelDetailRepository.save(detail);
        return convertToResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!hostelDetailRepository.existsById(id)) {
            throw new RuntimeException("Hostel detail not found with id: " + id);
        }
        hostelDetailRepository.deleteById(id);
    }

    private HostelDetailResponse convertToResponse(HostelDetail detail) {
        return new HostelDetailResponse(
                detail.getId(),
                detail.getHostel().getId(),
                // detail.getHostel().getName(),
                detail.getName(),
                detail.getLevelId(),
                detail.getIntakeId(),
                detail.getGenderId(),
                detail.getCreatedAt(),
                detail.getUpdatedAt()
        );
    }
}