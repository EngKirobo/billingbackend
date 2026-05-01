package com.billing.hostel.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class RoomResponse {
    private Integer id;
    private Integer hdetailsId;
    private Integer bedId;
    private Integer priceId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}