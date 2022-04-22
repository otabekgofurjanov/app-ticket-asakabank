package com.example.appticketasakabank.model.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {

    @NotNull
    @Size(min = 4,max = 30)
    private String firstName;

    @NotNull
    @Size(min = 4,max = 30)
    private String lastName;

    @NotNull
    private String username;
    @NotNull
    private String password;

}
