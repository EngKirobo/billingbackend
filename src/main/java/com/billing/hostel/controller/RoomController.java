package com.billing.hostel.controller;

import com.billing.hostel.dto.RoomRequest;
import com.billing.hostel.dto.RoomResponse;
import com.billing.hostel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<List<RoomResponse>> getAll() {
        return ResponseEntity.ok(roomService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_READ')")
    public ResponseEntity<RoomResponse> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(roomService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROOMS_CREATE')")
    public ResponseEntity<RoomResponse> create(@RequestBody RoomRequest request) {
        return new ResponseEntity<>(roomService.save(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_UPDATE')")
    public ResponseEntity<RoomResponse> update(@PathVariable Integer id, @RequestBody RoomRequest request) {
        return ResponseEntity.ok(roomService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOMS_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        roomService.delete(id);
        return ResponseEntity.noContent().build();
    }
}