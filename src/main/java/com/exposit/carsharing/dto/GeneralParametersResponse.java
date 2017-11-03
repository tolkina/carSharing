package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralParametersResponse extends AbstractResponse {
    private String brand;
    private String model;
    private Integer yearOfIssue;
}
