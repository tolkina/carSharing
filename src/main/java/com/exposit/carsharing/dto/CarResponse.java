package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarResponse implements Serializable {
    private Long id;
    private GeneralParametersResponse generalParameters;
    private TechnicalParametersResponse technicalParameters;
    private CurrentConditionResponse currentCondition;
    private ProfileResponse owner;
}
