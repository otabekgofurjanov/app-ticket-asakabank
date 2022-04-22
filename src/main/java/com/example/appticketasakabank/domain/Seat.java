package com.example.appticketasakabank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.enums.SeatStatus;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seat extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;

    @Column(nullable = false)
    private Integer rowNumber;

    @Column(nullable = false)
    private Integer columnNumber;

    @ManyToOne(optional = false)
    private Hall hall;

}
