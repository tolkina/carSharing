package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.CurrentCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentConditionRepository extends JpaRepository<CurrentCondition, Long> {

}
