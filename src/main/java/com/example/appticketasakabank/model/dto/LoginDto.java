package com.example.appticketasakabank.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    @NotNull
    @Size(min = 8, message = "min 8 ta belgidan iborat bo'lsihi kerak")
    private String username;
    @NotNull
    @Size(min = 4, max = 16,message = "min 4 ta belgidan iborat bo'lsihi kerak")
    private String password;

}
