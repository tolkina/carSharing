package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProfileRequest implements Serializable {
    private LocalDate birthday;
    private double drivingExperience;
}
