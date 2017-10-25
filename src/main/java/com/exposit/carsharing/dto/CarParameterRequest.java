package com.exposit.carsharing.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class CarParameterRequest implements Serializable {
    @NotBlank
    private String name;
}
