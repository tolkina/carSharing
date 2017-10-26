package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @OneToOne(mappedBy = "car")
    private GeneralParameters generalParameters;

    @OneToOne(mappedBy = "car")
    private TechnicalParameters technicalParameters;

    @OneToOne(mappedBy = "car")
    private CurrentCondition currentCondition;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "ad_id")
    private Ad ad;
}
