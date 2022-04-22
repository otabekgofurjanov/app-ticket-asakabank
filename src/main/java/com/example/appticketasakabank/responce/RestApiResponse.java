package com.example.appticketasakabank.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestApiResponse {

    private String message;
    private boolean success;
}
