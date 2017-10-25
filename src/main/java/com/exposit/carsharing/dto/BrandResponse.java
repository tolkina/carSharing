package com.exposit.carsharing.dto;

import lombok.Data;

import java.util.Set;

@Data
public class BrandResponse {
    private Long id;
    private String name;
    Set<CarParameterResponse> models;
}
