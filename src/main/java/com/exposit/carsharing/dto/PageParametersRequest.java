package com.exposit.carsharing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;

@Getter
@AllArgsConstructor
public class PageParametersRequest {
    @Min(1)
    Integer page;
    @Min(1)
    Integer size;
    SortType sortType;
}
