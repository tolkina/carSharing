package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralParametersResponse implements Serializable {
    private Long id;
    private String brand;
    private String model;
    private Integer yearOfIssue;
    private CarResponse car;
}
