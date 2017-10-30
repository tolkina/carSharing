package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ModelResponse implements Serializable {
    private Long id;
    private String name;
    private CarParameterResponse brand;
}
