package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreditCardResponse extends AbstractResponse {
    private String firstName;
    private String lastName;
    private String number;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validUntil;
}
