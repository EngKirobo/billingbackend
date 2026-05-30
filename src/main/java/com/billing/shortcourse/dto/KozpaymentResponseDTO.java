package com.billing.shortcourse.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KozpaymentResponseDTO {

    private Integer id;

    private Integer coursebookingId;

    private String controlNumber;

    private LocalDateTime paymentDate;

    private Boolean status;

    private String payerName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}