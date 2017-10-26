package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "current_condition")
@NoArgsConstructor
@Getter
@Setter
public class CurrentCondition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean damage;

    @Column(name = "damage_description")
    private String damageDescription;

    private double mileage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}
