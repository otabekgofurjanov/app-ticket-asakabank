package com.example.appticketasakabank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatDTO {

    private String seatStatus;
    private Integer rowNumber;
    private Integer columnNumber;
    private Long hallId;
}
