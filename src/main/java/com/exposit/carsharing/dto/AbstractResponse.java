package com.exposit.carsharing.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
abstract class AbstractResponse implements Serializable {
    private Long id;
}
