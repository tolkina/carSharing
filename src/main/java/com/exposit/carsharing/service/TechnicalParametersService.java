package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.TechnicalParametersRequest;
import com.exposit.carsharing.dto.TechnicalParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.TechnicalParameters;

import java.util.List;

public interface TechnicalParametersService {
    boolean isExist(Long technicalParametersId);

    TechnicalParametersResponse get(Long id) throws EntityNotFoundException;

    TechnicalParameters load(Long id) throws EntityNotFoundException;

    List<TechnicalParametersResponse> getAll();

    TechnicalParametersResponse create(TechnicalParametersRequest technicalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long technicalParameterId, Long carId) throws PrivilegeException, EntityNotFoundException;

    void check(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException;

    TechnicalParametersResponse mapParametersToResponse(TechnicalParameters technicalParameters);
}
