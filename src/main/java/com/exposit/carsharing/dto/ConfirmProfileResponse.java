package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.ConfirmProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ConfirmProfileResponse extends AbstractResponse {
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private double drivingExperience;
    private ConfirmProfile confirmProfile;
    private PassportDataResponse passportDataResponse;
    private DriverLicenseResponse driverLicenseResponse;
}
