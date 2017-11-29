package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.AccountStatus;
import com.exposit.carsharing.domain.ConfirmProfile;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmailAndStatus(String email, AccountStatus status);

    Profile findByEmail(String email);

    Page<Profile> findByConfirmProfile(ConfirmProfile confirmProfile, Pageable pageable);
}
