package com.exposit.carsharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car")
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
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "ad_id")
    private Ad ad;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GeneralParameters getGeneralParameters() {
        return generalParameters;
    }

    public void setGeneralParameters(GeneralParameters generalParameters) {
        this.generalParameters = generalParameters;
    }

    public TechnicalParameters getTechnicalParameters() {
        return technicalParameters;
    }

    public void setTechnicalParameters(TechnicalParameters technicalParameters) {
        this.technicalParameters = technicalParameters;
    }

    public CurrentCondition getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(CurrentCondition currentCondition) {
        this.currentCondition = currentCondition;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }
}
