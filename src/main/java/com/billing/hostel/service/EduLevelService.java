package com.billing.hostel.service;

import com.billing.hostel.dto.EduLevelRequest;
import com.billing.hostel.entity.EduLevel;
import com.billing.hostel.repository.EduLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EduLevelService {

    private final EduLevelRepository eduLevelRepository;

    // CREATE
    public EduLevel create(EduLevelRequest request) {
        EduLevel level = EduLevel.builder()
                .eduLevel(request.getEduLevel())
                .build();

        return eduLevelRepository.save(level);
    }

    // READ ALL
    public List<EduLevel> getAll() {
        return eduLevelRepository.findAll();
    }

    // READ ONE
    public EduLevel getById(Long id) {
        return eduLevelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("EduLevel not found"));
    }

    // UPDATE
    public EduLevel update(Long id, EduLevelRequest request) {
        EduLevel level = getById(id);
        level.setEduLevel(request.getEduLevel());
        return eduLevelRepository.save(level);
    }

    // DELETE
    public void delete(Long id) {
        eduLevelRepository.deleteById(id);
    }
}