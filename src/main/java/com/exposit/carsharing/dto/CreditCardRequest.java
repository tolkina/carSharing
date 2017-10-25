package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CreditCardRequest implements Serializable {
    private String firstName;
    private String lastName;
    private Integer number;
    private Date validUntil;
}
