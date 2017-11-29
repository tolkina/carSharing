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
public class UserRequest implements Serializable {
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @NotBlank
    private String email;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    @NotBlank
    private String password;
    @Pattern(regexp = Constants.PASSWORD_REGEX)
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String login;
}
