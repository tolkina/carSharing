package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmProfileResponse extends AbstractResponse {
    private String login;
    private PassportDataResponse passportDataResponse;
    private DriverLicenseResponse driverLicenseResponse;
}
