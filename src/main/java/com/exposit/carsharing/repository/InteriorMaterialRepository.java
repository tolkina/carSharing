package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.InteriorMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteriorMaterialRepository extends JpaRepository<InteriorMaterial, Long> {
    InteriorMaterial findByName(String name);
}
