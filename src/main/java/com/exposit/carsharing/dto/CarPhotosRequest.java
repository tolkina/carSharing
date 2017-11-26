package com.exposit.carsharing.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Getter
public class CarPhotosRequest implements Serializable {
    @NotEmpty
    @Valid
    private List<String> photos;
}
