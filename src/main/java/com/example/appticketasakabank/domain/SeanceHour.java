package com.example.appticketasakabank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SeanceHour extends AbsLongEntity {

    @Column(name = "seance_hour", nullable = false)
    @Min(1)
    @Max(24)
    private Integer hour;

    @ManyToOne(optional = false)
    private Seance seance;
}
