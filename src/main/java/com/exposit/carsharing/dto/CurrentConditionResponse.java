package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentConditionResponse extends AbstractResponse {
    private String damageDescription;
    private double mileage;
}
