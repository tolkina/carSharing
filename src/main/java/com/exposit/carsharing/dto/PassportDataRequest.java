package com.exposit.carsharing.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PassportDataRequest implements Serializable {
    private String firstName;
    private String lastName;
    private String middleName;
    private String series;
    private Integer number;
    private String personalNumber;
    private Date dateOfIssue;
    private String placeOfIssue;
    private Date validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}
