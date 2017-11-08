package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalParametersResponse extends AbstractResponse {
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
    private String govNumber;
    private Integer stsFormNumber;
    private String stsImageLink;
    private String ptsImageLink;
}
