package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CarRequest implements Serializable {
    private GeneralParametersRequest generalParameters;
    private TechnicalParametersRequest technicalParameters;
    private CurrentConditionRequest currentCondition;
}
