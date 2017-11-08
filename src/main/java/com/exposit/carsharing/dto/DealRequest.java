package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DealRequest implements Serializable {
    @NotNull
    private LocalDate bookingStartTime;
    @NotBlank
    private LocalDate rentalStartTime;
    @NotBlank
    private LocalDate estimatedRentalEndTime;
    @NotBlank
    private LocalDate rentalEndTime;
    @NotBlank
    private BigDecimal deposit;
    @NotBlank
    private BigDecimal price;
}
