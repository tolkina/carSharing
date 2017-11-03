package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
public class TechnicalParametersRequest implements Serializable {
    private String gearbox;
    private String bodyType;
    @Max(2)
    private Integer seatNumber;
    @Max(2)
    private Integer doorNumber;
    private String fuelType;
    private Double fuelConsumption;
    private String driveUnit;
    private String tiresSeason;
    private String interiorMaterial;
    private String color;
    @Max(20)
    private Integer vin;
    @Max(20)
    private String govNumber;
    private Integer stsFormNumber;
    private String stsImageLink;
    private String ptsImageLink;
}
