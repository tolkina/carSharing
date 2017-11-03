package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CreditCardRequest implements Serializable {
    @Max(20)
    @NotBlank
    private String firstName;
    @Max(20)
    @NotBlank
    private String lastName;
    @Max(20)
    @NotNull
    private Integer number;
    @NotBlank
    private Date validUntil;
}
