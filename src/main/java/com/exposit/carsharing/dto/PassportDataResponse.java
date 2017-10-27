package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PassportDataResponse implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String series;
    private Integer number;
    private String personalNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfIssue;
    private String placeOfIssue;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date validUntil;
    private String registrationPhotoUrl;
    private String photoUrl;
}
