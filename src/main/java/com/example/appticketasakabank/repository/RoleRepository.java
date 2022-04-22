package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.appticketasakabank.model.Role;
import com.example.appticketasakabank.model.enums.RoleEnum;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findAllByName(RoleEnum name);
}
