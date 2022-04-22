package com.example.appticketasakabank.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.example.appticketasakabank.model.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Component
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Override
    public String getAuthority() {
        return name.name();
    }

}
