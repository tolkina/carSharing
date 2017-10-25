package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CurrentConditionResponse implements Serializable {
    private Long id;
    private boolean damage;
    private String damageDescription;
    private double mileage;
    private CarResponse car;
}
