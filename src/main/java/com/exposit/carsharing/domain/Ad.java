package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ad")
public class Ad extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany(mappedBy = "ad", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Deal> deals;

    public Ad() {
        this.status = AdStatus.ACTUAL;
    }
}
