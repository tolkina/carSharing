package com.exposit.carsharing.dto;

import org.hibernate.validator.constraints.NotBlank;

public class TechnicalParameterDto {
    @NotBlank
    private String name;

    public TechnicalParameterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
