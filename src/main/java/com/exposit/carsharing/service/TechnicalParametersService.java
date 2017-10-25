package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.TechnicalParameters;

import java.util.List;

public interface TechnicalParametersService {
    boolean isExist(Long technicalParametersId);

    TechnicalParameters get(Long id) throws EntityNotFoundException;

    List<TechnicalParameters> getAll();

    void create(TechnicalParameters technicalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long technicalParameterId, Long carId) throws PrivilegeException, EntityNotFoundException;

    void check(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException;
}
