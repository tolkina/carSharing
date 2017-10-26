package com.exposit.carsharing.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
@Data
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GeneralParameters generalParameters;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TechnicalParameters technicalParameters;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CurrentCondition currentCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
