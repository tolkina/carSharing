package com.exposit.carsharing.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
/**
 * Created by Sergei on 10/12/2017.
 */

@Entity
@Table(name = "currentCondition")
public class CurrentCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "gearbox", length = 15)
    private String gearbox;             //Коробка передач

    @Column(name = "bodytype")
    private String bodyType;

    @Column(name = "seatNumber", length = 2)
    private Integer seatNumber;         //Кол-во мест

    @Column(name = "doorNumber", length = 1)
    private Integer doorNumber;

    @Column(name = "fuelType")
    private String fuelType;

    @Column(name = "fuelConsumption")
    private Double fuelConsumption;     //Расход топлива

    @Column(name = "driveUnit")
    private String driveUnit;           //Привод

    @Column(name = "tiresSeason")
    private String tiresSeason;         //Шины

    @Column(name = "interiorMaterial")
    private String interiorMaterial;

    @Column(name = "color")
    private String color;

    @Column(name = "vin")
    private Integer vin;

    @Column(name = "govNumber")
    private String govNumber;

    @Column(name = "stsFormNumber")
    private Integer stsFormNumber;

    @Column(name = "stsImageLink")
    private String stsImageLink;

    @Column(name = "ptsImageLink")
    private String ptsImageLink;

    public CurrentCondition() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setStsImageLink(String stsImagelink) {
        this.stsImageLink = stsImagelink;
    }

    public String getPtsImageLink() {
        return ptsImageLink;
    }

    public void setPtsImageLink(String ptsImageLink) {
        this.ptsImageLink = ptsImageLink;
    }


}
