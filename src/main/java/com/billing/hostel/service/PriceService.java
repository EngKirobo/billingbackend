package com.billing.hostel.service;

import com.billing.hostel.dto.PriceRequest;
import com.billing.hostel.entity.Price;
import com.billing.hostel.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    // CREATE
    public Price create(PriceRequest request) {
        Price price = Price.builder()
                .amount(request.getAmount())
                .build();

        return priceRepository.save(price);
    }

    // GET ALL
    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    // GET ONE
    public Price getById(Integer id) {
        return priceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Price not found"));
    }

    // UPDATE
    public Price update(Integer id, PriceRequest request) {
        Price price = getById(id);
        price.setAmount(request.getAmount());
        return priceRepository.save(price);
    }

    // DELETE
    public void delete(Integer id) {
        priceRepository.deleteById(id);
    }
}