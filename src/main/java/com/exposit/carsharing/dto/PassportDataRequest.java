package com.exposit.carsharing.dto;

import com.exposit.carsharing.util.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class PassportDataRequest implements Serializable {
    @Size(max = 20)
    private String firstName;
    @Size(max = 20)
    private String lastName;
    @Size(max = 20)
    private String middleName;
    @Pattern(regexp = Constants.SERIES_AND_NUMBER_REGEX)
    private String seriesAndNumber;
    @Pattern(regexp = Constants.PERSONAL_NUMBER_REGEX)
    private String personalNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    private LocalDate validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}