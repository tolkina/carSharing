package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.DriverLicense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverLicenseRepository extends JpaRepository<DriverLicense, Long>{
}
