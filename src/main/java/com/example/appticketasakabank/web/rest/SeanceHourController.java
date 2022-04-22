package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appticketasakabank.domain.SeanceHour;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeanceHourDTO;
import com.example.appticketasakabank.service.SeanceHourService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeanceHourController {

    @Autowired
    private SeanceHourService seanceHourService;

    @PostMapping("/seance-hour")
    public ResponseEntity<?> create(@RequestBody SeanceHourDTO seanceHourDTO) {
        RestApiResponse apiResponse = seanceHourService.create(seanceHourDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/seance-hours")
    public ResponseEntity<List<?>> seanceHourList() {
        List<SeanceHour> seanceHourList = seanceHourService.seanceHourList();
        return ResponseEntity.ok(seanceHourList);
    }
}
