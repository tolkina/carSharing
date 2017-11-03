package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BrandResponse extends CarParameterResponse {
    Set<CarParameterResponse> models;
}
