package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
public class GeneralParametersRequest implements Serializable {
    private String brand;
    private String model;
    @Max(4)
    private Integer yearOfIssue;
}
