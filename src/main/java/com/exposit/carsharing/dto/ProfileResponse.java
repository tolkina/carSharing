package com.exposit.carsharing.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProfileResponse extends AbstractResponse {
    private String avatarUrl;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Double drivingExperience;
    private boolean confirmProfile;
}
