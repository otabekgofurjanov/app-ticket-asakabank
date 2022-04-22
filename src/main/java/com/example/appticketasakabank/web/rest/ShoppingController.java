package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appticketasakabank.responce.RestApiResponse;
import com.example.appticketasakabank.model.dto.ShoppingDTO;
import com.example.appticketasakabank.service.ShoppingService;

@RestController
@RequestMapping("/api")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/shopping")
    public ResponseEntity<?> shopping(@RequestBody ShoppingDTO shoppingDTO) {
        RestApiResponse apiResponse = shoppingService.shopping(shoppingDTO);
        return ResponseEntity.status(201).body(apiResponse);
    }
}
