package com.example.appticketasakabank.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.appticketasakabank.model.template.AbsLongEntity;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cinema extends AbsLongEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
