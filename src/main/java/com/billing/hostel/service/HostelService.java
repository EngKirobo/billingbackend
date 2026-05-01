package com.billing.hostel.service;

import com.billing.hostel.dto.HostelRequest;
import com.billing.hostel.dto.HostelResponse;
import com.billing.hostel.entity.Hostel;
import com.billing.hostel.repository.HostelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HostelService {

    private final HostelRepository hostelRepository;

    public List<HostelResponse> getAll() {
        return hostelRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public HostelResponse getById(Long id) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));
        return convertToResponse(hostel);
    }

    @Transactional
    public HostelResponse create(HostelRequest request) {
        Hostel hostel = new Hostel();
        hostel.setName(request.getName());
        hostel.setDescription(request.getDescription());
        hostel.setLocation(request.getLocation());
        hostel.setCapacity(request.getCapacity());

        Hostel saved = hostelRepository.save(hostel);
        return convertToResponse(saved);
    }

    @Transactional
    public HostelResponse update(Long id, HostelRequest request) {
        Hostel hostel = hostelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hostel not found with id: " + id));

        hostel.setName(request.getName());
        hostel.setDescription(request.getDescription());
        hostel.setLocation(request.getLocation());
        hostel.setCapacity(request.getCapacity());

        Hostel updated = hostelRepository.save(hostel);
        return convertToResponse(updated);
    }

    @Transactional
    public void delete(Long id) {
        if (!hostelRepository.existsById(id)) {
            throw new RuntimeException("Hostel not found with id: " + id);
        }
        hostelRepository.deleteById(id);
    }

    private HostelResponse convertToResponse(Hostel hostel) {
        return new HostelResponse(
                hostel.getId(),
                hostel.getName(),
                hostel.getDescription(),
                hostel.getLocation(),
                hostel.getCapacity(),
                hostel.getCreatedAt(),
                hostel.getUpdatedAt()
        );
    }
}