package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.appticketasakabank.domain.Shopping;
import com.example.appticketasakabank.model.enums.ShoppingStatus;
import com.example.appticketasakabank.repository.BookingRepository;
import com.example.appticketasakabank.repository.ShoppingRepository;

import java.util.Objects;
import java.util.Set;


@Component
public class BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingService.class);
    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Scheduled(fixedRate = 7200000L)
    public void bookingStatus() {
        log.info("Schudeled for ticket");
        Set<Shopping> shoppings = shoppingRepository.getShopping();
        for (Shopping shopping : shoppings) {
            if (Objects.equals(shopping.getPaySum(), shopping.getShowSeances().getPrice())) {
                shopping.setShoppingStatus(ShoppingStatus.PAYED);
                shoppingRepository.save(shopping);
            } else {
                shopping.setShoppingStatus(ShoppingStatus.FREE);
            }
        }
    }
}

