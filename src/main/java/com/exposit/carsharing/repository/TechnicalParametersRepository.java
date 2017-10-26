package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.TechnicalParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalParametersRepository extends JpaRepository<TechnicalParameters, Long> {
    TechnicalParameters findByCar(Car car);
}
