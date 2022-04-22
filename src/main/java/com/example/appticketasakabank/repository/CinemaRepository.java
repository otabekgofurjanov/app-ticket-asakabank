package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.Cinema;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}
