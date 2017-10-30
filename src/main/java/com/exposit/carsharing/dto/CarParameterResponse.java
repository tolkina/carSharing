package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarParameterResponse implements Serializable {
    private Long id;
    private String name;
}
