package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.TechnicalParameters;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicalParametersRepository extends JpaRepository<TechnicalParameters, Long> {
}
