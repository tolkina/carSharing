package com.exposit.carsharing.dto;

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
    @Size(max = 2)
    private String series;
    @Pattern(regexp = "[0-9]{7,8}")
    private String number;
    @Size(max = 20)
    private String personalNumber;
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    private LocalDate validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}