package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DealRequest implements Serializable {
    @NotNull
    private Date bookingStartTime;
    @NotBlank
    private Date rentalStartTime;
    @NotBlank
    private Date estimatedRentalEndTime;
    @NotBlank
    private Date rentalEndTime;
    @NotBlank
    private BigDecimal deposit;
    @NotBlank
    private BigDecimal price;
}
