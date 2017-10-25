package com.exposit.carsharing.dto;

import lombok.Data;

@Data
public class DriverLicenseResponse {
    private Long id;
    private String seriesAndNumber;
    private Character category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
