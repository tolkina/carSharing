package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DealResponse extends AbstractResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long bookingStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long rentalStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long estimatedRentalEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Long rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
    private ProfileResponse owner;
    private ProfileResponse customer;
    private AdResponse ad;
}
