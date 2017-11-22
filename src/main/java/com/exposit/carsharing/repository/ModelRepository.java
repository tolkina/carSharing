package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Brand;
import com.exposit.carsharing.domain.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByName(String name);

    Page<Model> findAllByBrand(Brand brand, Pageable pageable);

    Model findByNameAndBrand(String model, Brand brand);

    Long countByBrand(Brand brand);
}
