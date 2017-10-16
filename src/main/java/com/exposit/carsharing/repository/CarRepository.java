package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOwner(Profile owner);
}
