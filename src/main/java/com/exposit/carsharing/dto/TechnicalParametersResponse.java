package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TechnicalParametersResponse implements Serializable {
    private Long id;
    private String gearbox;
    private String bodyType;
    private Integer seatNumber;
    private Integer doorNumber;
    private String fuelType;
    private Double fuelConsumption;
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
