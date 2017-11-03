package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DealRequest implements Serializable {
    private Date bookingStartTime;
    private Date rentalStartTime;
    private Date estimatedRentalEndTime;
    private Date rentalEndTime;
    private BigDecimal deposit;
    private BigDecimal price;
}
