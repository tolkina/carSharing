package com.exposit.carsharing.dto;

import com.exposit.carsharing.configuration.Constants;
import com.exposit.carsharing.model.CreditCard;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

public class ProfileDto extends AbstractDto {
    @Pattern(regexp = Constants.EMAIL_REGEX)
    private String email;

    @Pattern(regexp = Constants.PASSWORD_REGEX)
    private String password;

    private String avatarUrl;

    private Date birthday;

    private double drivingExperience;

    private boolean confirmProfile;

    private Set<CreditCard> creditCards;

    public ProfileDto() {
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
}
