package com.exposit.carsharing.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
public class CarPhotosRequest implements Serializable {
    @NotNull
    private List<String> photos;
}
