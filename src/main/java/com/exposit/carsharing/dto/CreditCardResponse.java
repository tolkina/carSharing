package com.exposit.carsharing.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CreditCardResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer number;
    private Date validUntil;
}
