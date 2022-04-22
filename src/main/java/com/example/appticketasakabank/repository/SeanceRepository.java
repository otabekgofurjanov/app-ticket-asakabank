package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.Seance;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {
}
