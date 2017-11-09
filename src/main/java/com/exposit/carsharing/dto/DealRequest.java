package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DealRequest implements Serializable {
    @NotNull
    private LocalDate bookingStartTime;
    @NotNull
    private LocalDate rentalStartTime;
    @NotNull
    private LocalDate estimatedRentalEndTime;
    @NotNull
    private LocalDate rentalEndTime;
    @NotNull
    private BigDecimal deposit;
    @NotNull
    private BigDecimal price;
}
