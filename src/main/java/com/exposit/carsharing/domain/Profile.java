package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile extends AbstractEntity {
    private String email;

    private String password;

    @Column(name = "avatar")
    private String avatarUrl;

    private LocalDate birthday;

    @Column(name = "driving_experience")
    private double drivingExperience;

    @Enumerated(value = EnumType.STRING)
    private ConfirmProfile confirmProfile;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CreditCard> creditCards;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private DriverLicense driverLicense;

    @OneToOne(mappedBy = "owner", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private PassportData passportData;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Ad> ads;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Deal> dealsWithMe;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, orphanRemoval = true)
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

    @Column(name = "count_of_overdue_booking")
    private long countOfOverdueBooking;

    public Profile() {
        this.confirmProfile = ConfirmProfile.NO;
    }
}
