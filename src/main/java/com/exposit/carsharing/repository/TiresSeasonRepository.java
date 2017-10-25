package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.TiresSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiresSeasonRepository extends JpaRepository<TiresSeason, Long> {
    TiresSeason findByName(String name);
}
