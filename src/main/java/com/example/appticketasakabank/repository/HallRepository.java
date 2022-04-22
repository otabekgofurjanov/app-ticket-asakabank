package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long> {
}
