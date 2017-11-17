package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.GeneralParameters;
import com.exposit.carsharing.domain.TechnicalParameters;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface CarService {
    // ------------------------------- Car ----------------------------------------------------
    boolean isExist(Long id);

    Car getCar(Long id) throws EntityNotFoundException;

    CarResponse getCarResponse(Long id) throws EntityNotFoundException;

    List<CarResponse> getAll();

    List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    List<CarResponse> getAllWithoutAdByOwner(Long ownerId) throws EntityNotFoundException;

    CarResponse create(CarRequest car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException;

    void checkCarOwner(Car car, Long ownerId) throws PrivilegeException;

    // ------------------------------- Technical Parameters -------------------------------------

    TechnicalParametersResponse getTechnicalParameters(Long carId) throws EntityNotFoundException;

    List<TechnicalParametersResponse> getAllTechnicalParameters();

    TechnicalParametersResponse updateTechnicalParameters(TechnicalParametersRequest technicalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void checkTechnicalParameters(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException;

    // ------------------------------- General Parameters ---------------------------------------

    GeneralParametersResponse getGeneralParameters(Long carId) throws EntityNotFoundException;

    List<GeneralParametersResponse> getAllGeneralParameters();

    GeneralParametersResponse updateGeneralParameters(GeneralParametersRequest generalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void checkGeneralParameters(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException;


    // ------------------------------- Current Condition ----------------------------------------

    CurrentConditionResponse getCurrentCondition(Long carId) throws EntityNotFoundException;

    List<CurrentConditionResponse> getAllCurrentCondition();

    CurrentConditionResponse updateCurrentCondition(CurrentConditionRequest currentConditionRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;


}
