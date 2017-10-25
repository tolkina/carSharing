package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentConditionRequest implements Serializable {
    private boolean damage;
    private String damageDescription;
    private double mileage;
}
