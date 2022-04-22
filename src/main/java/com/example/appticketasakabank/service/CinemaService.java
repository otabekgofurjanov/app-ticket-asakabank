package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Cinema;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.CinemaDTO;
import com.example.appticketasakabank.repository.CinemaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaService {

    private final Logger log = LoggerFactory.getLogger(CinemaService.class);

    @Autowired
    private CinemaRepository cinemaRepository;

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse create(CinemaDTO cinemaDTO) {
        Cinema cinema = new Cinema();
        cinema.setName(cinemaDTO.getName());
        log.debug("Request to save cinema : {}", cinemaDTO);
        cinemaRepository.save(cinema);
        return new RestApiResponse("Cinema saved", true);
    }

    public Cinema getById(Long id) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(id);
        if (!optionalCinema.isPresent()) {
            return null;
        }
        return optionalCinema.get();
    }

    public List<Cinema> getAll() {
        List<Cinema> cinemaList = cinemaRepository.findAll();
        return cinemaList;
    }

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse edit(Long id, CinemaDTO cinemaDTO) {
        Optional<Cinema> optionalCinema = cinemaRepository.findById(id);
        if (optionalCinema.isPresent()) {
            Cinema cinema = optionalCinema.get();
            cinema.setName(cinemaDTO.getName());
            log.debug("Request to edit cinema : {}", cinemaDTO);
            cinemaRepository.save(cinema);
            return new RestApiResponse("Success editing cinema", true);
        }
        return new RestApiResponse("Cinema id not found", false);
    }

    @PreAuthorize("hasRole('MANAGER')")
    public boolean delete(Long id) {
        try {
            cinemaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
