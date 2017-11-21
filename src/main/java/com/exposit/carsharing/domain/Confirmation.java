package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "confirmation")
public class Confirmation extends AbstractEntity {
    @Enumerated(value = EnumType.STRING)
    @Column(name = "confirm_profile")
    private ConfirmProfile confirmProfile;

    @Column(name = "date_confirm")
    private LocalDateTime dateConfirm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @Column(name = "passport_first_name")
    private String passportFirstName;

    @Column(name = "passport_last_name")
    private String passportLastName;

    @Column(name = "passport_middle_name")
    private String passportMiddleName;

    @Column(name = "passport_series_and_number")
    private String passportSeriesAndNumber;

    @Column(name = "passport_personal_number")
    private String passportPersonalNumber;

    @Column(name = "passport_date_of_issue")
    private LocalDate passportDateOfIssue;

    @Column(name = "passport_place_of_issue")
    private String passportPlaceOfIssue;

    @Column(name = "passport_valid_until")
    private LocalDate passportValidUntil;

    @Column(name = "passport_registration_photo")
    private String passportRegistrationPhotoUrl;

    @Column(name = "passport_photo")
    private String passportPhotoUrl;

    @Column(name = "driver_license_series_and_number")
    private String driverLicenseSeriesAndNumber;

    @Column(name = "driver_license_category")
    private String driverLicenseCategory;

    @Column(name = "driver_license_font_side_image")
    private String driverLicenseFrontSideImageUrl;

    @Column(name = "driver_license_back_side_image")
    private String driverLicenseBackSideImageUrl;
}
