package com.billing.hostel.dto;

import lombok.Data;

@Data // If using Lombok, otherwise generate Getters/Setters
public class IntakeDTO {
    private Long id;
    private int intake;
}