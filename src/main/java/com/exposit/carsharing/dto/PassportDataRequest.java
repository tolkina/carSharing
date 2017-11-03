package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class PassportDataRequest implements Serializable {
    @Max(20)
    private String firstName;
    @Max(20)
    private String lastName;
    @Max(20)
    private String middleName;
    @Max(2)
    private String series;
    @Max(10)
    private Integer number;
    @Max(15)
    private String personalNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    private LocalDate validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}