package com.billing.hostel.service;

import com.billing.hostel.dto.RoomRequest;
import com.billing.hostel.dto.RoomResponse;
import com.billing.hostel.entity.Room;
import com.billing.hostel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<RoomResponse> getAll() {
        return roomRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public RoomResponse getById(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        return mapToResponse(room);
    }

    public RoomResponse save(RoomRequest request) {
        Room room = new Room();
        room.setHdetailsId(request.getHdetailsId());
        room.setBedId(request.getBedId());
        room.setPriceId(request.getPriceId());
        return mapToResponse(roomRepository.save(room));
    }

    public RoomResponse update(Integer id, RoomRequest request) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        
        room.setHdetailsId(request.getHdetailsId());
        room.setBedId(request.getBedId());
        room.setPriceId(request.getPriceId());
        
        return mapToResponse(roomRepository.save(room));
    }

    public void delete(Integer id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Room not found");
        }
        roomRepository.deleteById(id);
    }

    private RoomResponse mapToResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .hdetailsId(room.getHdetailsId())
                .bedId(room.getBedId())
                .priceId(room.getPriceId())
                .createdAt(room.getCreatedAt())
                .updatedAt(room.getUpdatedAt())
                .build();
    }
}