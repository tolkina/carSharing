package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.CurrentCondition;

import java.util.List;

public interface CurrentConditionService {
    boolean isExist(Long currentConditionId);

    CurrentCondition get(Long id) throws EntityNotFoundException;

    List<CurrentCondition> getAll();

    void create(CurrentCondition currentCondition, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long currentConditionId, Long carId) throws PrivilegeException, EntityNotFoundException;
}