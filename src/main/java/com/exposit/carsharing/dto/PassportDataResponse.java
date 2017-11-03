package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassportDataResponse extends AbstractResponse {
    private String firstName;
    private String lastName;
    private String middleName;
    private String series;
    private Integer number;
    private String personalNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;
    private String placeOfIssue;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}
