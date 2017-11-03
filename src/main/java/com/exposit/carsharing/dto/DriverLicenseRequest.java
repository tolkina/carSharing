package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
public class DriverLicenseRequest implements Serializable {
    @Max(20)
    private String seriesAndNumber;
    @Max(5)
    private String category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
