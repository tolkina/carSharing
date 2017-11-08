package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GeneralParametersRequest implements Serializable {
    private String brand;
    private String model;
    private Integer yearOfIssue;
}
