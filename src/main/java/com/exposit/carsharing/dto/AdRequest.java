package com.exposit.carsharing.dto;

import com.exposit.carsharing.domain.AdStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class AdRequest implements Serializable {
    private AdStatus status;
    private String carLocation;
    private String returnPlace;
    private double costPerHour;
    private double CostPerDay;
    private double CostPer3Days;
}
