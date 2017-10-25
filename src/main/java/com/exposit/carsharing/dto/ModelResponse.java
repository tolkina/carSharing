package com.exposit.carsharing.dto;

import lombok.Data;

@Data
public class ModelResponse {
    private Long id;
    private String name;
    private CarParameterResponse brand;
}
