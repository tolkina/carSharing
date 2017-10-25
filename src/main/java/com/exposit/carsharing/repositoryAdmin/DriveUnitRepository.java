package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.DriveUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriveUnitRepository extends JpaRepository<DriveUnit, Long> {
    DriveUnit findByName(String driveName);
}
