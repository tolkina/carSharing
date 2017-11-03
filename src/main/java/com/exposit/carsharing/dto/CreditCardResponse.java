package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CreditCardResponse extends AbstractResponse {
    private String firstName;
    private String lastName;
    private Integer number;
    private Date validUntil;
}
