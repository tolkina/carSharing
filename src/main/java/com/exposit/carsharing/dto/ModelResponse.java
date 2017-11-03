package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelResponse extends CarParameterResponse {
    private CarParameterResponse brand;
}
