package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "driver_license")
public class DriverLicense extends AbstractEntity {
    @Column(name = "series_and_number")
    private String seriesAndNumber;

    private String category;

    @Column(name = "font_side_image")
    private String frontSideImageUrl;

    @Column(name = "back_side_image")
    private String backSideImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;
}
