package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarRequest implements Serializable {
    private GeneralParametersRequest generalParameters;
    private TechnicalParametersRequest technicalParameters;
    private CurrentConditionRequest currentCondition;
}
