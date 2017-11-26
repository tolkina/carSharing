package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "general_parameters")
public class GeneralParameters extends AbstractEntity {
    @ElementCollection
    private List<String> photos = new ArrayList<>();

    private String brand;

    private String model;

    @Column(name = "year_of_issue", length = 4)
    private Integer yearOfIssue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
}
