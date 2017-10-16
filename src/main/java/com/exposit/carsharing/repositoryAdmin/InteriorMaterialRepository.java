package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.BodyType;
import com.exposit.carsharing.modelAdmin.InteriorMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteriorMaterialRepository extends JpaRepository<InteriorMaterial, Long> {
    InteriorMaterial findByInteriorMaterial(String interiorMaterial);
}
