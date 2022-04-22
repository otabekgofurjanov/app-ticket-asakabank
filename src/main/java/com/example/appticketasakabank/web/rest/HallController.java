package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.appticketasakabank.domain.Hall;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.HallDTO;
import com.example.appticketasakabank.service.HallService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HallController {

    @Autowired
    private HallService hallService;

    @PostMapping("/hall")
    public ResponseEntity<?> create(@RequestBody HallDTO hallDTO) {
        RestApiResponse apiResponse = hallService.create(hallDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }

    @GetMapping("/hall/list")
    public ResponseEntity<List<?>> hallList() {
        List<Hall> hallList = hallService.hallList();
        return ResponseEntity.ok(hallList);
    }

    @GetMapping("/hall/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Hall getById = hallService.getById(id);
        return ResponseEntity.status(200).body(getById);
    }

    @PutMapping("hall/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody HallDTO hallDTO) {
        RestApiResponse apiResponse = hallService.edit(id, hallDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }

    @DeleteMapping("/hall/{id}")
    public boolean delete(@PathVariable Long id) {
        boolean delete = hallService.delete(id);
        return delete;
    }
}
