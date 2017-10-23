package com.exposit.carsharing.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "profile")
public class Profile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(name = "avatar")
    private String avatarUrl;

    private Date birthday;

    @Column(name = "driving_experience")
    private double drivingExperience;

    private boolean confirmProfile;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<CreditCard> creditCards;

    @OneToOne(mappedBy = "owner")
    private DriverLicense driverLicense;

    @OneToOne(mappedBy = "owner")
    private PassportData passportData;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Ad> ads;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> dealsWithMe;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Deal> myDeals;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Car> cars;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public double getDrivingExperience() {
        return drivingExperience;
    }

    public void setDrivingExperience(double drivingExperience) {
        this.drivingExperience = drivingExperience;
    }

    public boolean isConfirmProfile() {
        return confirmProfile;
    }

    public void setConfirmProfile(boolean confirmProfile) {
        this.confirmProfile = confirmProfile;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(DriverLicense driverLicense) {
        this.driverLicense = driverLicense;
    }

    public PassportData getPassportData() {
        return passportData;
    }

    public void setPassportData(PassportData passportData) {
        this.passportData = passportData;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

    public Set<Deal> getDealsWithMe() {
        return dealsWithMe;
    }

    public void setDealsWithMe(Set<Deal> dealsWithMe) {
        this.dealsWithMe = dealsWithMe;
    }

    public Set<Deal> getMyDeals() {
        return myDeals;
    }

    public void setMyDeals(Set<Deal> myDeals) {
        this.myDeals = myDeals;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
