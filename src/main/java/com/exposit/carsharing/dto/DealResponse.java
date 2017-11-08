package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DealResponse extends AbstractResponse {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate estimatedRentalEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
    private ProfileResponse owner;
    private ProfileResponse customer;
}
