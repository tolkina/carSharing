package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "technical_parameters")
public class TechnicalParameters implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parameter", length = 15)
    private String gearbox;             //Коробка передач

    @Column(name = "body_type")
    private String bodyType;

    @Column(name = "seat_number", length = 2)
    private Integer seatNumber;         //Кол-во мест

    @Column(name = "door_number", length = 1)
    private Integer doorNumber;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "fuel_consumption")
    private Double fuelConsumption;     //Расход топлива

    @Column(name = "drive_unit")
    private String driveUnit;           //Привод

    @Column(name = "tires_season")
    private String tiresSeason;         //Шины

    @Column(name = "interior_material")
    private String interiorMaterial;

    private String color;

    private Integer vin;

    @Column(name = "gov_number")
    private String govNumber;

    @Column(name = "sts_form_number")
    private Integer stsFormNumber;

    @Column(name = "sts_image_link")
    private String stsImageLink;

    @Column(name = "pts_image_link")
    private String ptsImageLink;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public TechnicalParameters() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Integer getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(Integer doorNumber) {
        this.doorNumber = doorNumber;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(Double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public String getDriveUnit() {
        return driveUnit;
    }

    public void setDriveUnit(String driveUnit) {
        this.driveUnit = driveUnit;
    }

    public String getTiresSeason() {
        return tiresSeason;
    }

    public void setTiresSeason(String tiresSeason) {
        this.tiresSeason = tiresSeason;
    }

    public String getInteriorMaterial() {
        return interiorMaterial;
    }

    public void setInteriorMaterial(String interiorMaterial) {
        this.interiorMaterial = interiorMaterial;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getVin() {
        return vin;
    }

    public void setVin(Integer vin) {
        this.vin = vin;
    }

    public String getGovNumber() {
        return govNumber;
    }

    public void setGovNumber(String govNumber) {
        this.govNumber = govNumber;
    }

    public Integer getStsFormNumber() {
        return stsFormNumber;
    }

    public void setStsFormNumber(Integer stsFormNumber) {
        this.stsFormNumber = stsFormNumber;
    }

    public String getStsImageLink() {
        return stsImageLink;
    }

    public void setStsImageLink(String stsImageLink) {
        this.stsImageLink = stsImageLink;
    }

    public String getPtsImageLink() {
        return ptsImageLink;
    }

    public void setPtsImageLink(String ptsImageLink) {
        this.ptsImageLink = ptsImageLink;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
