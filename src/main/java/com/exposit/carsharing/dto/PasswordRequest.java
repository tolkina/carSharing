package com.exposit.carsharing.dto;

import com.exposit.carsharing.util.Constants;
import cz.jirutka.validator.spring.SpELAssert;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
@SpELAssert(value = "password.equals(confirmPassword)", message = "Passwords are not the same")
public class PasswordRequest implements Serializable {
    @NotBlank
    private String oldPassword;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    @NotBlank
    private String password;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    @NotBlank
    private String confirmPassword;
}