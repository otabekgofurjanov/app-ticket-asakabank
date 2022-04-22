package com.example.appticketasakabank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.enums.BookingStatus;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking extends AbsLongEntity {

    @OneToOne(optional = false)
    private Shopping shopping;

    @Column(name = "waiting_minute")
    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;
}
