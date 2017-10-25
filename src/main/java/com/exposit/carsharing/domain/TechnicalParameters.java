package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "technical_parameters")
@Data
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
}
