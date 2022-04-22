package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.SeanceHour;

@Repository
public interface SeanceHourRepository extends JpaRepository<SeanceHour, Long> {
}
