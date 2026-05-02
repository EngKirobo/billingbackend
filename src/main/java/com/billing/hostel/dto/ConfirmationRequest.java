package com.billing.hostel.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ConfirmationRequest {

    private String billId;
    private String fullName;
    private String payRefId;
    private String controlNumber;
    private LocalDate pdates;
    private BigDecimal amountPaid;
    private String payMethod;
    private String receiptNo;
}