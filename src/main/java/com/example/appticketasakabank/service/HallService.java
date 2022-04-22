package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Cinema;
import com.example.appticketasakabank.domain.Hall;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.HallDTO;
import com.example.appticketasakabank.repository.CinemaRepository;
import com.example.appticketasakabank.repository.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HallService {

    private final Logger log = LoggerFactory.getLogger(CinemaService.class);

    @Autowired
    private HallRepository hallRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse create(HallDTO hallDTO) {
        Hall hall = new Hall();
        hall.setName(hallDTO.getName());

        Optional<Cinema> optionalCinema = cinemaRepository.findById(hallDTO.getCinemaId());
        if (!optionalCinema.isPresent()) {
            return new RestApiResponse("Cinema id not found", false);
        }
        hall.setCinema(optionalCinema.get());
        log.debug("Request to save hall : {}", hallDTO);
        hallRepository.save(hall);
        return new RestApiResponse("Hall successfylly saved", true);
    }

    public Hall getById(Long id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            return hall;
        }
        return null;
    }

    public List<Hall> hallList() {
        List<Hall> hallList = hallRepository.findAll();
        return hallList;
    }

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse edit(Long id, HallDTO hallDTO) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            hall.setName(hallDTO.getName());
            log.debug("Request to edit hall : {}", hallDTO);
            hallRepository.save(hall);
            return new RestApiResponse("Hall edited", true);
        }
        return new RestApiResponse("Hall not found", false);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public boolean delete(Long id) {
        try {
            hallRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
