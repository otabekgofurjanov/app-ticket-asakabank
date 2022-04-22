package com.example.appticketasakabank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceHourDTO {
    private Integer hour;
    private Long seanceId;
}
