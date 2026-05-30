package com.billing.shortcourse.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KozpaymentRequestDTO {

    private Integer coursebookingId;

    private String controlNumber;

    private LocalDateTime paymentDate;

    private Boolean status;

    private String payerName;
}