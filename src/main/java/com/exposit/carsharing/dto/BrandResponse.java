package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class BrandResponse implements Serializable {
    private Long id;
    private String name;
    Set<CarParameterResponse> models;
}
