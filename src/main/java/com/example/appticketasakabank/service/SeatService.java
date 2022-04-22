package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Hall;
import com.example.appticketasakabank.domain.Seat;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeatDTO;
import com.example.appticketasakabank.model.enums.SeatStatus;
import com.example.appticketasakabank.repository.HallRepository;
import com.example.appticketasakabank.repository.SeatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private final Logger log = LoggerFactory.getLogger(SeatService.class);

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private HallRepository hallRepository;

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse create(SeatDTO seatDTO) {
        Seat seat = new Seat();
        seat.setRowNumber(seatDTO.getRowNumber());
        seat.setColumnNumber(seatDTO.getColumnNumber());
        seat.setSeatStatus(SeatStatus.FREE);

        Optional<Hall> optionalHall = hallRepository.findById(seatDTO.getHallId());
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            seat.setHall(hall);
            log.debug("Request to save seat : {}", seatDTO);
            seatRepository.save(seat);
            return new RestApiResponse("Seat success created", true);
        }
        return new RestApiResponse("Hall is not found", false);
    }

    public Seat getById(Long id) {
        Optional<Seat> optionalSeat = seatRepository.findById(id);
        if (optionalSeat.isPresent()) {
            Seat seat = optionalSeat.get();
            return seat;
        }
        return null;
    }

    public List<Seat> seatList() {
        List<Seat> seatList = seatRepository.findAll();
        return seatList;
    }
}
