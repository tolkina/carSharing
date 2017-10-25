package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profile")
@NoArgsConstructor
@Getter
@Setter
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(length = 60)
    private String password;

    @Column(name = "avatar")
    private String avatarUrl;

    private Date birthday;

    @Column(name = "driving_experience")
    private double drivingExperience;

    private boolean confirmProfile;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CreditCard> creditCards;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private DriverLicense driverLicense;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private PassportData passportData;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Ad> ads;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> dealsWithMe;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> myDeals;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Car> cars;
}
