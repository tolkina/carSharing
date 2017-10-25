package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType findByName(String name);
}
