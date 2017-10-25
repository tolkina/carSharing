package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findByName(String name);
}
