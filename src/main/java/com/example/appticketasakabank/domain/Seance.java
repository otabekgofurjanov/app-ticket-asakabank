package com.example.appticketasakabank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.enums.SeanceType;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seance extends AbsLongEntity {

    @Enumerated(EnumType.STRING)
    private SeanceType seanceType;

    @Column(nullable = false)
    private Date seanceDate;

    @Column(nullable = false)
    private String movieName;

    @Column(nullable = false)
    private Date meetingDate;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    private Hall hall;

}
