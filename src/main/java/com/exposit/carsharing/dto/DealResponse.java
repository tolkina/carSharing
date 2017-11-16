package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.DealStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DealResponse extends AbstractResponse {
    private DealStatus status;
    private Long bookingStartTime;
    private Long rentalStartTime;
    private Long estimatedRentalEndTime;
    private Long rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
    private ProfileResponse owner;
    private ProfileResponse customer;
    private AdResponse ad;
    private CreditCardResponse creditCard;
    private Long hoursForRent;
}
