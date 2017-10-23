package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findById(Long id);
    Brand findByName(String name);
}
