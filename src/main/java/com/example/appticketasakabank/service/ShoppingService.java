package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Hall;
import com.example.appticketasakabank.domain.Seance;
import com.example.appticketasakabank.domain.Seat;
import com.example.appticketasakabank.domain.Shopping;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.ShoppingDTO;
import com.example.appticketasakabank.model.enums.SeatStatus;
import com.example.appticketasakabank.model.enums.ShoppingStatus;
import com.example.appticketasakabank.repository.HallRepository;
import com.example.appticketasakabank.repository.SeanceRepository;
import com.example.appticketasakabank.repository.SeatRepository;
import com.example.appticketasakabank.repository.ShoppingRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingService {

    private final Logger log = LoggerFactory.getLogger(SeanceService.class);

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private SeatRepository seatRepository;

    public RestApiResponse shopping(ShoppingDTO shoppingDTO) {
        Shopping shopping = new Shopping();
        shopping.setPaySum(shoppingDTO.getPaySum());
        shopping.setCustomerKey(shoppingDTO.getCustomerKey());
        shopping.setShoppingStatus(ShoppingStatus.valueOf(shoppingDTO.getShoppingStatus()));

        Optional<Hall> optionalHall = hallRepository.findById(shoppingDTO.getHall_id());
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            shopping.setHall(hall);
        } else {
            return new RestApiResponse("Hall is not found", false);
        }

        Optional<Seance> optionalSeance = seanceRepository.findById(shoppingDTO.getShowSeancesId());
        if (optionalSeance.isPresent()) {
            Seance seance = optionalSeance.get();
            shopping.setShowSeances(seance);
        } else {
            return new RestApiResponse("Seance is not found", false);
        }

        Optional<Seat> optionalSeat = seatRepository.findById(shoppingDTO.getSeatId());
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            Optional<Seance> seanceOptional = seanceRepository.findById(shoppingDTO.getShowSeancesId());
            if (!seanceOptional.isPresent()) {
                return null;
            }
            Seance seance = optionalSeance.get();
            if ((seat.getSeatStatus() == SeatStatus.FREE) && Objects.equals(shopping.getPaySum(), seance.getPrice())) {
                seat.setSeatStatus(SeatStatus.PURCHASE);
                shopping.setShoppingStatus(ShoppingStatus.PAYED);
                shopping.setSeats(seat);
            } else if ((seat.getSeatStatus() == SeatStatus.FREE) && (shopping.getPaySum() < seance.getPrice())) {
                seat.setSeatStatus(SeatStatus.BOOKING);
                shopping.setShoppingStatus(ShoppingStatus.BOOKING);
                shopping.setSeats(seat);
            } else if ((seat.getSeatStatus() == SeatStatus.PURCHASE) && (shopping.getShoppingStatus() == ShoppingStatus.PAYED)) {
                seat.setSeatStatus(SeatStatus.ACTIVE);
                shopping.setSeats(seat);
            } else if (seat.getSeatStatus() == SeatStatus.NOT_SERVICE) {
                seat.setSeatStatus(SeatStatus.NOT_SERVICE);
                shopping.setSeats(seat);
            } else
                return new RestApiResponse("Seat is not found", false);
        }
        log.debug("Request to save shopping : {}", shoppingDTO);
        shoppingRepository.save(shopping);
        return new RestApiResponse("Seat successfully shopping", true);
    }

    public List<Shopping> shoppingList() {
        List<Shopping> list = shoppingRepository.findAll();
        return list;
    }

}
