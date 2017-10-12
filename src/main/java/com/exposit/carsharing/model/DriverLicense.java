package com.exposit.carsharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "driver_license")
public class DriverLicense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_and_number")
    private String seriesAndNumber;

    private Character category;

    @Column(name = "font_side_image")
    private String frontSideImageUrl;

    @Column(name = "back_side_image")
    private String backSideImageUrl;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    public DriverLicense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeriesAndNumber() {
        return seriesAndNumber;
    }

    public void setSeriesAndNumber(String seriesAndNumber) {
        this.seriesAndNumber = seriesAndNumber;
    }

    public Character getCategory() {
        return category;
    }

    public void setCategory(Character category) {
        this.category = category;
    }

    public String getFrontSideImageUrl() {
        return frontSideImageUrl;
    }

    public void setFrontSideImageUrl(String frontSideImageUrl) {
        this.frontSideImageUrl = frontSideImageUrl;
    }

    public String getBackSideImageUrl() {
        return backSideImageUrl;
    }

    public void setBackSideImageUrl(String backSideImageUrl) {
        this.backSideImageUrl = backSideImageUrl;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }
}
