package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GeneralParametersRequest implements Serializable {
    private String brand;
    private String model;
    private Integer yearOfIssue;
}
