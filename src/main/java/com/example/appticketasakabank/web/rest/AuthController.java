package com.example.appticketasakabank.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.appticketasakabank.model.dto.LoginDto;
import com.example.appticketasakabank.model.dto.RegisterDto;
import com.example.appticketasakabank.responce.ApiResponse;
import com.example.appticketasakabank.security.JWTprovider;
import com.example.appticketasakabank.service.MyAuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyAuthService myAuthService;

    @Autowired
    JWTprovider jwTprovider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ApiResponse logintoSystem(@RequestBody LoginDto loginDto) {
        return myAuthService.login(loginDto);
    }

    @PostMapping("/register")
    public ApiResponse register(@Valid @RequestBody RegisterDto registerDto) {
        return myAuthService.registerEmployee(registerDto);
    }


}
