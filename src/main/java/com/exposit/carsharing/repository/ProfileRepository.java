package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.ConfirmProfile;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmail(String email);

    List<Profile> findByConfirmProfile(ConfirmProfile confirmProfile);
}
