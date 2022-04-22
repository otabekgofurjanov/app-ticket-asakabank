package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appticketasakabank.domain.Cinema;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.CinemaDTO;
import com.example.appticketasakabank.service.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CinemaController {

    @Autowired
    private CinemaService cinemaService;

    @PostMapping("/cinema")
    public ResponseEntity<?> save(@RequestBody CinemaDTO cinemaDTO) {
        RestApiResponse apiResponse = cinemaService.create(cinemaDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/cinema/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Cinema serviceById = cinemaService.getById(id);
        return ResponseEntity.ok(serviceById);
    }

    @GetMapping("/cinema/list")
    public ResponseEntity<List<?>> getAllList() {
        List<Cinema> cinemaList = cinemaService.getAll();
        return ResponseEntity.ok(cinemaList);
    }

    @PutMapping("/cinema/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody CinemaDTO cinemaDTO) {
        RestApiResponse edit = cinemaService.edit(id, cinemaDTO);
        return ResponseEntity.status(edit.isSuccess() ? 202 : 409).body(edit);
    }

    @DeleteMapping("/cinema/{id}")
    public boolean deleteById(@PathVariable Long id) {
        boolean delete = cinemaService.delete(id);
        return delete;
    }
}
