package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DealResponse extends AbstractResponse {
    private Date bookingStartTime;
    private Date rentalStartTime;
    private Date estimatedRentalEndTime;
    private Date rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
    private ProfileResponse owner;
    private ProfileResponse customer;
}
