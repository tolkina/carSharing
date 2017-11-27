package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOwnerAndAd(Profile owner, Ad ad);
}
