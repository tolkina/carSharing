package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DriverLicenseRequest implements Serializable {
    private String seriesAndNumber;
    private Character category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
