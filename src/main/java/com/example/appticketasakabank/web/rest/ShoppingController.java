package com.example.appticketasakabank.web.rest;

import com.example.appticketasakabank.model.dto.CancelShoppingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PutMapping("/shopping/{id}")
    public ResponseEntity<?> cancelShop(@PathVariable Long id, @RequestBody CancelShoppingDto cancelShoppingDto) {
        RestApiResponse apiResponse = shoppingService.cancelled(id, cancelShoppingDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse);
    }
}
