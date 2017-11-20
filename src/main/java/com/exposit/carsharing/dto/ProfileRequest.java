package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class ProfileRequest implements Serializable {
    private LocalDate birthday;
    @Min(0)
    private double drivingExperience;
    @NotBlank
    @Size(max = 20)
    private String login;
}
