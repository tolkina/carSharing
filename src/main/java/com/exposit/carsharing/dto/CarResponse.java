package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse extends AbstractResponse {
    private GeneralParametersResponse generalParameters;
    private TechnicalParametersResponse technicalParameters;
    private CurrentConditionResponse currentCondition;
    private ProfileResponse owner;
}
