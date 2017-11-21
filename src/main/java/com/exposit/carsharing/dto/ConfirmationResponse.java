package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.ConfirmProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ConfirmationResponse extends AbstractResponse {
    private ConfirmProfile confirmProfile;
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime dateConfirm;
    private Long profileId;
    private String profileLogin;
    private String passportFirstName;
    private String passportLastName;
    private String passportMiddleName;
    private String passportSeriesAndNumber;
    private String passportPersonalNumber;
    private LocalDate passportDateOfIssue;
    private String passportPlaceOfIssue;
    private LocalDate passportValidUntil;
    private String passportRegistrationPhotoUrl;
    private String passportPhotoUrl;
    private String driverLicenseSeriesAndNumber;
    private String driverLicenseCategory;
    private String driverLicenseFrontSideImageUrl;
    private String driverLicenseBackSideImageUrl;
}
