package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.TechnicalParameters;
import com.exposit.carsharing.dto.TechnicalParametersRequest;
import com.exposit.carsharing.dto.TechnicalParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface TechnicalParametersService {
    TechnicalParametersResponse get(Long carId) throws EntityNotFoundException;

    List<TechnicalParametersResponse> getAll();

    TechnicalParametersResponse update(TechnicalParametersRequest technicalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void check(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException;
}
