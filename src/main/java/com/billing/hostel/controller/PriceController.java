package com.billing.hostel.controller;

import com.billing.hostel.dto.PriceRequest;
import com.billing.hostel.entity.Price;
import com.billing.hostel.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prices")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")   // Better than "*"
public class PriceController {

    private final PriceService priceService;

    // GET ALL - Currently public (you had commented @PreAuthorize)
    @GetMapping
    public ResponseEntity<List<Price>> getAll() {
        return ResponseEntity.ok(priceService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PRICE_READ')")
    public ResponseEntity<Price> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(priceService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('PRICE_CREATE')")
    public ResponseEntity<Price> create(@RequestBody PriceRequest request) {
        return ResponseEntity.ok(priceService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PRICE_UPDATE')")
    public ResponseEntity<Price> update(@PathVariable Integer id, 
                                        @RequestBody PriceRequest request) {
        return ResponseEntity.ok(priceService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRICE_DELETE')")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        priceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

// package com.billing.hostel.controller;

// import com.billing.hostel.dto.PriceRequest;
// import com.billing.hostel.entity.Price;
// import com.billing.hostel.service.PriceService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/prices")
// @RequiredArgsConstructor
// @EnableMethodSecurity  // ✅ VERY IMPORTANT
// @CrossOrigin("*")
// public class PriceController {

//     private final PriceService priceService;

//     // 🔒 CREATE
//     @PreAuthorize("hasAuthority('PRICE_CREATE')")
//     @PostMapping
//     public Price create(@RequestBody PriceRequest request) {
//         return priceService.create(request);
//     }

//     // 🔓 READ ALL
//     @PreAuthorize("hasAuthority('PRICE_READ')")
//     @GetMapping
//     public List<Price> getAll() {
//         return priceService.getAll();
//     }

//     // 🔓 READ ONE
//     @PreAuthorize("hasAuthority('PRICE_READ')")
//     @GetMapping("/{id}")
//     public Price getById(@PathVariable Integer id) {
//         return priceService.getById(id);
//     }

//     // 🔒 UPDATE
//     @PreAuthorize("hasAuthority('PRICE_UPDATE')")
//     @PutMapping("/{id}")
//     public Price update(@PathVariable Integer id,
//                         @RequestBody PriceRequest request) {
//         return priceService.update(id, request);
//     }

//     // 🔒 DELETE
//     @PreAuthorize("hasAuthority('PRICE_DELETE')")
//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Integer id) {
//         priceService.delete(id);
//     }
// }