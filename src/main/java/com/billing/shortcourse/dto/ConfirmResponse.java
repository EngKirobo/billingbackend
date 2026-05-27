package com.billing.shortcourse.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class ConfirmResponse {

    private Integer payid;
    private String billId;
    private String fullName;
    private String payRefId;
    private String controlNumber;
    private LocalDate pdates;
    private BigDecimal amountPaid;
    private String payMethod;
    private String receiptNo;
}