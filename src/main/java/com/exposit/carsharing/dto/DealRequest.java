package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class DealRequest implements Serializable {
    @NotNull
    private Long adId;
    @NotNull
    @Min(1)
    @Max(72)
    private Long hoursOfRent;
    @NotNull
    private Long creditCardId;
}
