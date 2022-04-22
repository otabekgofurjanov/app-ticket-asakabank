package com.example.appticketasakabank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingDTO {
    private Double paySum;
    private String customerKey;
    private Long hall_id;
    private Long showSeancesId;
    private Long seatId;
    private String shoppingStatus;
}
