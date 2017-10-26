package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.GeneralParameters;
import com.exposit.carsharing.dto.GeneralParametersRequest;
import com.exposit.carsharing.dto.GeneralParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface GeneralParametersService {
    GeneralParametersResponse get(Long carId) throws EntityNotFoundException;

    List<GeneralParametersResponse> getAll();

    GeneralParametersResponse update(GeneralParametersRequest generalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void check(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException;
}
