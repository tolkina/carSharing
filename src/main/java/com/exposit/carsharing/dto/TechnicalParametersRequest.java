package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class TechnicalParametersRequest implements Serializable {
    private String gearbox;
    private String bodyType;
    private Integer seatNumber;
    private Integer doorNumber;
    private String fuelType;
    private double fuelConsumption;
    private String driveUnit;
    private String tiresSeason;
    private String interiorMaterial;
    private String color;
    private Integer vin;
    @Size(max = 20)
    private String govNumber;
    private Integer stsFormNumber;
    private String stsImageLink;
    private String ptsImageLink;
}
