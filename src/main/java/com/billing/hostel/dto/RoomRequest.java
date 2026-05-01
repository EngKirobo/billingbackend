package com.billing.hostel.dto;

import lombok.Data;

@Data
public class RoomRequest {
    private Integer hdetailsId;
    private Integer bedId;
    private Integer priceId;
}