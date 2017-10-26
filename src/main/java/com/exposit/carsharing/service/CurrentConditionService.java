package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.CurrentCondition;
import com.exposit.carsharing.dto.CurrentConditionRequest;
import com.exposit.carsharing.dto.CurrentConditionResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface CurrentConditionService {
    CurrentConditionResponse get(Long carId) throws EntityNotFoundException;

    List<CurrentConditionResponse> getAll();

    CurrentConditionResponse update(CurrentConditionRequest currentConditionRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;
}