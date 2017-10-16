package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.GeneralParameters;

import java.util.List;

public interface GeneralParametersService {
    boolean isExist(Long generalParametersId);

    GeneralParameters get(Long id) throws EntityNotFoundException;

    List<GeneralParameters> getAll();

    void create(GeneralParameters generalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long generalParametersId, Long carId) throws PrivilegeException, EntityNotFoundException;
}
