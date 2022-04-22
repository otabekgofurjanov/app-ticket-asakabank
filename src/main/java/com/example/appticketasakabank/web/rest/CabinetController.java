package com.example.appticketasakabank.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appticketasakabank.model.User;

@RestController
@RequestMapping("/api/mycabinet")
@RequiredArgsConstructor
public class CabinetController {

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<? > getMyInf(){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user.getUsername()+" "+user.getFirstName()+" "+user.getLastName());
    }

}
