package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long>{
    DriverLicense findByOwner(Profile owner);
}
