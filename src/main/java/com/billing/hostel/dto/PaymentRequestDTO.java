package com.billing.hostel.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long hostelbookingId;
    private String controlNumber;
    private Boolean status;
}