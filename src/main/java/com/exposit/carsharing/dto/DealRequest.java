package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class DealRequest implements Serializable {
    private Date bookingStartTime;
    private Date rentalStartTime;
    private Date estimatedRentalEndTime;
    private Date rentalEndTime;
    private double deposit;
    private double price;
}
