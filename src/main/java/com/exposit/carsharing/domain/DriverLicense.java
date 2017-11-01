package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "driver_license")
@Data
public class DriverLicense implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "series_and_number")
    private String seriesAndNumber;

    private String category;

    @Column(name = "font_side_image")
    private String frontSideImageUrl;

    @Column(name = "back_side_image")
    private String backSideImageUrl;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;
}
