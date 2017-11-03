package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profile")
public class Profile extends AbstractEntity {
    private String email;

    private String password;

    @Column(name = "avatar")
    private String avatarUrl;

    private Date birthday;

    @Column(name = "driving_experience")
    private Double drivingExperience;

    private boolean confirmProfile;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CreditCard> creditCards;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private DriverLicense driverLicense;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private PassportData passportData;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Ad> ads;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> dealsWithMe;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> myDeals;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Car> cars;

    @ManyToMany
    @JoinTable(
            name = "profiles_roles",
            joinColumns = @JoinColumn(
                    name = "profile_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
