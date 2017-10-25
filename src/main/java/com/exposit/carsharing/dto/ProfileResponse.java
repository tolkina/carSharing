package com.exposit.carsharing.dto;

import com.exposit.carsharing.configuration.Constants;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Data
public class ProfileResponse {
    private Long id;
    @Pattern(regexp = Constants.EMAIL_REGEX)
    private String email;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;
    private String avatarUrl;
    private Date birthday;
    private double drivingExperience;
    private boolean confirmProfile;
    private Set<CreditCardResponse> creditCards;
    private DriverLicenseResponse driverLicense;
}
