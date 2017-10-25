package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.AdStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdResponse implements Serializable {
    private Long id;
    private AdStatus status;
    private String carLocation;
    private String returnPlace;
    private double costPerHour;
    private double CostPerDay;
    private double CostPer3Days;
    private ProfileResponse owner;
    private CarResponse car;
}