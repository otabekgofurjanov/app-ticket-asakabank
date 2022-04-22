package com.example.appticketasakabank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.domain.Seance;
import com.example.appticketasakabank.domain.SeanceHour;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeanceHourDTO;
import com.example.appticketasakabank.repository.SeanceHourRepository;
import com.example.appticketasakabank.repository.SeanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SeanceHourService {

    @Autowired
    private SeanceHourRepository seanceHourRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @PreAuthorize("hasRole('MANAGER')")
    public RestApiResponse create(SeanceHourDTO seanceHourDTO) {
        SeanceHour seanceHour = new SeanceHour();
        seanceHour.setHour(seanceHourDTO.getHour());

        Optional<Seance> optionalSeance = seanceRepository.findById(seanceHourDTO.getSeanceId());
        if (optionalSeance.isPresent()) {
            Seance seance = optionalSeance.get();
            seanceHour.setSeance(seance);
            seanceHourRepository.save(seanceHour);
            return new RestApiResponse("Seance hour successfully created", true);
        }
        return new RestApiResponse("Seanse not found ", false);
    }

    public List<SeanceHour> seanceHourList() {
        List<SeanceHour> seanceHours = seanceHourRepository.findAll();
        return seanceHours;
    }
}
