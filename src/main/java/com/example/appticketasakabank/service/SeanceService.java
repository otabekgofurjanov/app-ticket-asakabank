package com.example.appticketasakabank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Hall;
import com.example.appticketasakabank.domain.Seance;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeanceDTO;
import com.example.appticketasakabank.model.enums.SeanceType;
import com.example.appticketasakabank.repository.HallRepository;
import com.example.appticketasakabank.repository.SeanceRepository;

import java.util.List;
import java.util.Optional;

;

@Service
public class SeanceService {

    private final Logger log = LoggerFactory.getLogger(SeanceService.class);


    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private HallRepository hallRepository;

    public RestApiResponse save(SeanceDTO seanceDTO) {
        Seance seance = new Seance();
        seance.setSeanceType(SeanceType.valueOf(seanceDTO.getSeanceTypeName()));
        seance.setSeanceDate(seanceDTO.getSeanceDate());
        seance.setMovieName(seanceDTO.getMovieName());
        seance.setMeetingDate(seanceDTO.getMeetingDate());
        seance.setPrice(seanceDTO.getPrice());

        Optional<Hall> optionalHall = hallRepository.findById(seanceDTO.getHallId());
        if (!optionalHall.isPresent()) {
            return new RestApiResponse("Not found hall_id", false);
        }
        Hall hall = optionalHall.get();
        seance.setHall(hall);
        log.debug("Request to save Seance : {}", seanceDTO);
        seanceRepository.save(seance);
        return new RestApiResponse("Seccessfully saved seance", true);
    }

    public RestApiResponse edit(Long id, SeanceDTO seanceDTO) {
        Optional<Seance> optionalSeance = seanceRepository.findById(id);
        if (optionalSeance.isPresent()) {
            Seance seance = optionalSeance.get();
            seance.setSeanceType(SeanceType.valueOf(seanceDTO.getSeanceTypeName()));
            seance.setSeanceDate(seanceDTO.getSeanceDate());
            seance.setPrice(seanceDTO.getPrice());
            seance.setMeetingDate(seanceDTO.getMeetingDate());
            seance.setMovieName(seanceDTO.getMovieName());

            Optional<Hall> optionalHall = hallRepository.findById(seanceDTO.getHallId());
            if (!optionalHall.isPresent()) {
                return new RestApiResponse("Not found hall_id", false);
            }
            Hall hall = optionalHall.get();
            seance.setHall(hall);
            log.debug("Request to edit Seance : {}, {}", id, seanceDTO);
            seanceRepository.save(seance);
            return new RestApiResponse("Seccessfully edited seance", true);
        }
        return new RestApiResponse("Seanse not found ", false);
    }

    public List<Seance> seanceList() {
        List<Seance> seanceList = seanceRepository.findAll();
        return seanceList;
    }

    public Seance getById(Long id) {
        log.debug("Request to getById Seance : {}", id);
        Optional<Seance> optionalSeance = seanceRepository.findById(id);
        if (optionalSeance.isPresent()) {
            return optionalSeance.get();
        }
        return null;
    }

    public boolean delete(Long id) {
        log.debug("Request to delete Seance : {}", id);
        try {
            seanceRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
