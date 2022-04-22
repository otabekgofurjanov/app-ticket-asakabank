package com.example.appticketasakabank.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {

    private Long shoppingId;
    private LocalDateTime waiting;
    private String bookingStatus;

}
