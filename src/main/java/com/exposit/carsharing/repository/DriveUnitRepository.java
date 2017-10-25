package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.DriveUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriveUnitRepository extends JpaRepository<DriveUnit, Long> {
    DriveUnit findByName(String driveName);
}
