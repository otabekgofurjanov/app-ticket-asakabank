package com.example.appticketasakabank.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.appticketasakabank.model.User;
import com.example.appticketasakabank.model.dto.LoginDto;
import com.example.appticketasakabank.model.dto.RegisterDto;
import com.example.appticketasakabank.model.enums.RoleEnum;
import com.example.appticketasakabank.repository.RoleRepository;
import com.example.appticketasakabank.repository.UserRepository;
import com.example.appticketasakabank.responce.ApiResponse;
import com.example.appticketasakabank.security.JWTprovider;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MyAuthService implements UserDetailsService {

    List<User> users;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JWTprovider jwTprovider;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw new UsernameNotFoundException(username + " topilmadi");
    }


    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()

            ));

            User user = (User) authenticate.getPrincipal();
            String token = jwTprovider.generateToken(user.getUsername());
            return new ApiResponse(token, true);

        } catch (BadCredentialsException e) {
            return new ApiResponse("parol yoki login xato", false);
        }
    }

    public ApiResponse registerEmployee(RegisterDto registerDto) {


        com.example.appticketasakabank.model.User user = new com.example.appticketasakabank.model.User();
        boolean existsByUsername = userRepository.existsByUsername(registerDto.getUsername());
        if (existsByUsername) return new ApiResponse("Bu username mavjud", false);

        user.setUsername(registerDto.getUsername());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findAllByName(RoleEnum.ROLE_USER)));
        user.setEnabled(true);
        userRepository.save(user);
        String generateToken = JWTprovider.generateToken(registerDto.getUsername());

        return new ApiResponse("Muvaffaqiyatli ro'yxatdan o'tdingiz. Token:  " + generateToken, true);

    }
}
