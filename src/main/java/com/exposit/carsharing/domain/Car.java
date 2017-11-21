package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "car")
public class Car extends AbstractEntity {
    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private GeneralParameters generalParameters;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TechnicalParameters technicalParameters;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CurrentCondition currentCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, orphanRemoval = true)
    private Ad ad;
}
