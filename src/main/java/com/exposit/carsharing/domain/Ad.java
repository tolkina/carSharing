package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ad")
@Data
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
}
