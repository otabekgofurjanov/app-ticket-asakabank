package com.example.appticketasakabank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatLoopDTO {

    private Integer row_number;
    private Integer column_number;
    private Long hall_Id;
}
