package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverLicenseResponse extends AbstractResponse {
    private String seriesAndNumber;
    private String category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
