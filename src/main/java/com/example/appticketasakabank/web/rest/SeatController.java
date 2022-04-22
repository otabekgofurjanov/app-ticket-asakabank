package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appticketasakabank.domain.Seat;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.SeatDTO;
import com.example.appticketasakabank.service.SeatService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<?> create(@RequestBody SeatDTO seatDTO) {
        RestApiResponse apiResponse = seatService.create(seatDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/seats")
    public ResponseEntity<List<?>> getAll() {
        List<Seat> seatList = seatService.seatList();
        return ResponseEntity.ok(seatList);
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Seat getById = seatService.getById(id);
        return ResponseEntity.ok(getById);
    }
}
