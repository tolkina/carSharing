package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DriverLicenseResponse implements Serializable {
    private Long id;
    private String seriesAndNumber;
    private String category;
    private String frontSideImageUrl;
    private String backSideImageUrl;
}
