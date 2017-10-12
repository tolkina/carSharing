package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.CurrentCondition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */
public interface CurrentConditionRepository extends JpaRepository<CurrentCondition, Long> {

}
