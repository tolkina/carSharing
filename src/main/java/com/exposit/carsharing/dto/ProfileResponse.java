package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Data
public class ProfileResponse implements Serializable {
    private Long id;
    private String avatarUrl;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private double drivingExperience;
    private boolean confirmProfile;
    private DriverLicenseResponse driverLicense;
    private PassportDataResponse passportData;
    private Collection<RoleResponse> roles;
}
