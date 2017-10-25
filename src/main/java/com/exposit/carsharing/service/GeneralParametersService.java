package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.GeneralParametersRequest;
import com.exposit.carsharing.dto.GeneralParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.GeneralParameters;

import java.util.List;

public interface GeneralParametersService {
    boolean isExist(Long generalParametersId);

    GeneralParametersResponse get(Long id) throws EntityNotFoundException;

    List<GeneralParametersResponse> getAll();

    GeneralParametersResponse create(GeneralParametersRequest generalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void delete(Long generalParametersId, Long carId) throws PrivilegeException, EntityNotFoundException;

    void check(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException;

    GeneralParametersResponse mapToResponse(GeneralParameters generalParameters);
}
