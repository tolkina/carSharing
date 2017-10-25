package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Brand;
import com.exposit.carsharing.domain.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByName(String name);
    List<Model> findAllByBrand(Brand brand);
    Model findByNameAndBrand(String model, Brand brand);
}
