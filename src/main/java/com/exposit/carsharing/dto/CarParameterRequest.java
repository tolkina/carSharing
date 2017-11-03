package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import java.io.Serializable;

@Getter
@Setter
public class CarParameterRequest implements Serializable {
    @NotBlank
    @Max(20)
    private String name;
}
