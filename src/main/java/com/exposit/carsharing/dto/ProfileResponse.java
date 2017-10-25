package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
public class ProfileResponse implements Serializable {
    private Long id;
    private String avatarUrl;
    private Date birthday;
    private double drivingExperience;
    private boolean confirmProfile;
    private Set<CreditCardResponse> creditCards;
    private DriverLicenseResponse driverLicense;
}
