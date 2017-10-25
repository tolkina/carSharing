package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.CurrentConditionRequest;
import com.exposit.carsharing.dto.CurrentConditionResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.CurrentCondition;

import java.util.List;

public interface CurrentConditionService {
    boolean isExist(Long currentConditionId);

    CurrentConditionResponse get(Long id) throws EntityNotFoundException;

    List<CurrentConditionResponse> getAll();

    CurrentConditionResponse create(CurrentConditionRequest currentCondition, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long currentConditionId, Long carId) throws PrivilegeException, EntityNotFoundException;

    CurrentConditionResponse mapToResponse(CurrentCondition currentCondition);
}