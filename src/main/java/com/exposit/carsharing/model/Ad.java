package com.exposit.carsharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ad")
public class Ad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AdStatus status;

    @Column(name = "car_location")
    private String carLocation;

    @Column(name = "return_place")
    private String returnPlace;

    @Column(name = "cost_per_hour")
    private double costPerHour;

    @Column(name = "cost_per_day")
    private double CostPerDay;

    @Column(name = "cost_per_3_days")
    private double CostPer3Days;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @OneToOne(mappedBy = "ad")
    private Car car;

    public Ad() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdStatus getStatus() {
        return status;
    }

    public void setStatus(AdStatus status) {
        this.status = status;
    }

    public String getCarLocation() {
        return carLocation;
    }

    public void setCarLocation(String carLocation) {
        this.carLocation = carLocation;
    }

    public String getReturnPlace() {
        return returnPlace;
    }

    public void setReturnPlace(String returnPlace) {
        this.returnPlace = returnPlace;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public double getCostPerDay() {
        return CostPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        CostPerDay = costPerDay;
    }

    public double getCostPer3Days() {
        return CostPer3Days;
    }

    public void setCostPer3Days(double costPer3Days) {
        CostPer3Days = costPer3Days;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
