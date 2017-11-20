package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.DealStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DealResponse extends AbstractResponse {
    private DealStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime bookingStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime rentalStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime estimatedRentalEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
    private ProfileResponse owner;
    private ProfileResponse customer;
    private AdResponse ad;
    private CreditCardResponse creditCard;
    private Long daysForRent;
}
