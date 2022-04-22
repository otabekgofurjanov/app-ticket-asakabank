package com.example.appticketasakabank.repository;

import com.example.appticketasakabank.domain.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Cinema, Long> {

}
