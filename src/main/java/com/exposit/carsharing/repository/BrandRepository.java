package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand findById(Long id);
    Brand findByName(String name);
}
