package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Sergei on 10/12/2017.
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}
