package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.TiresSeason;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiresSeasonRepository extends JpaRepository<TiresSeason, Long> {
    TiresSeason findByName(String name);
}
