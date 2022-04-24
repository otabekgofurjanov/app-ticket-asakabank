package com.example.appticketasakabank.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeanceDTO {
    private String seanceTypeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date seanceDate;
    private String movieName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date meetingDate;
    private Double price;
    private Long hallId;
}


