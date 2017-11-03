package com.exposit.carsharing.dto;

import com.exposit.carsharing.configuration.Constants;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class UserRequest implements Serializable {
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @NotBlank
    private String email;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    @NotBlank
    private String password;
}
