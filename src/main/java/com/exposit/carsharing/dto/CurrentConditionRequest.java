package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrentConditionRequest implements Serializable {
    private String damageDescription;
    private Double mileage;
}
