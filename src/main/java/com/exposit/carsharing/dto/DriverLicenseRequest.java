package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class DriverLicenseRequest implements Serializable {
    @Size(max = 20)
    private String seriesAndNumber;
    @Size(max = 5)
    private String category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
