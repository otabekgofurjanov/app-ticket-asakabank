package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}
