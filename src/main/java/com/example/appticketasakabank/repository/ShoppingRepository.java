package com.example.appticketasakabank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.appticketasakabank.domain.Shopping;

import java.util.Set;

@Repository
public interface ShoppingRepository extends JpaRepository<Shopping, Long> {

    @Query("select sh from Shopping sh where sh.shoppingStatus = 'BOOKING'")
    Set<Shopping> getShopping();
}
