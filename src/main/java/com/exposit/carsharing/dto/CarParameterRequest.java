package com.exposit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CarParameterRequest implements Serializable {
    @NotBlank
    @Size(max = 20)
    private String name;
}
