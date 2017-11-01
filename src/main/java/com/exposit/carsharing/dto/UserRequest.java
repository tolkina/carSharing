package com.exposit.carsharing.dto;

import com.exposit.carsharing.configuration.Constants;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Data
public class ProfileRequest implements Serializable {
    @Pattern(regexp = Constants.EMAIL_REGEX)
    private String email;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;
    private String avatarUrl;
    private Date birthday;
    private double drivingExperience;
    private boolean confirmProfile;
}
