package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ad")
public class Ad extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private AdStatus status;

    @Column(name = "car_location")
    private String carLocation;

    @Column(name = "return_place")
    private String returnPlace;

    @Column(name = "cost_per_hour")
    private BigDecimal costPerHour;

    @Column(name = "cost_per_day")
    private BigDecimal CostPerDay;

    @Column(name = "cost_per_3_days")
    private BigDecimal CostPer3Days;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

//    @OneToOne(mappedBy = "ad",fetch = FetchType.LAZY, orphanRemoval = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;
}
