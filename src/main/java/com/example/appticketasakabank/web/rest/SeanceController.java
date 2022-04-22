package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appticketasakabank.domain.Seance;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeanceDTO;
import com.example.appticketasakabank.service.SeanceService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeanceController {

    @Autowired
    private SeanceService seanceService;

    @PostMapping("/seance")
    public ResponseEntity<?> save(@RequestBody SeanceDTO seanceDTO) {
        RestApiResponse apiResponse = seanceService.save(seanceDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/seance/all")
    public ResponseEntity<?> seanseList() {
        List<Seance> seanceList = seanceService.seanceList();
        return ResponseEntity.ok(seanceList);
    }

    @GetMapping("/seance/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Seance getById = seanceService.getById(id);
        return ResponseEntity.ok(getById);
    }

    @PutMapping("/seance/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody SeanceDTO seanceDTO) {
        RestApiResponse apiResponse = seanceService.edit(id, seanceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/seance/{id}")
    public boolean delete(@PathVariable Long id) {
        boolean delete = seanceService.delete(id);
        return delete;
    }

}