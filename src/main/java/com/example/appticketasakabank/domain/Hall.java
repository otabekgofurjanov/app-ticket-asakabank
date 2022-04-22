package com.example.appticketasakabank.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hall extends AbsLongEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Cinema cinema;

}
