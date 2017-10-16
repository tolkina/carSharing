package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.Brand;
import com.exposit.carsharing.modelAdmin.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByModel(String model);
    List<Model> findAllByBrand(Brand brand);
}
