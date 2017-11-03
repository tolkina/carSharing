package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ProfileRequest implements Serializable {
    private Date birthday;
    private Double drivingExperience;
}
