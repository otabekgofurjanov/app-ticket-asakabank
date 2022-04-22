package com.example.appticketasakabank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.enums.ShoppingStatus;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shopping extends AbsLongEntity {

    @Column(nullable = false)
    private Double paySum;

    @Column(nullable = false)
    private String customerKey;

    @Enumerated(EnumType.STRING)
    private ShoppingStatus shoppingStatus;

    @ManyToOne(optional = false)
    private Hall hall;

    @ManyToOne
    private Seance showSeances;

    @ManyToOne
    private Seat seats;

}
