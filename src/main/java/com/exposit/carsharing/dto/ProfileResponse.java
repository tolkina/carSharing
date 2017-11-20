package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.ConfirmProfile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProfileResponse extends AbstractResponse {
    private String avatarUrl;
    private String email;
    private String login;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private double drivingExperience;
    private ConfirmProfile confirmProfile;
    private long countOfOverdueBooking;
}
