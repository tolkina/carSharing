package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Getter
@Setter
public class GeneralParametersRequest implements Serializable {
    @Min(1900)
    @Max(2018)
    private Integer yearOfIssue;
}
