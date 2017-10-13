package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.CurrentCondition;

import java.util.List;

public interface CurrentConditionService {
    CurrentCondition getCurrentCondition(Long id);

    List<CurrentCondition> getAllCurrentConditions();

    void createCurrentCondition(CurrentCondition currentCondition, Long carId) throws EntityNotFoundException;
}
