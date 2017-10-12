package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByEmail(String email);
}
