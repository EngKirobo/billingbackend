package com.billing.hostel.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentResponseDTO {

    private Integer id;
    private Long hostelbookingId;
    private String controlNumber;
    private LocalDateTime paymentDate;
    private Boolean status;
}