package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.GeneralParameters;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.AdException;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.io.IOException;
import java.util.List;

public interface CarService {
    // ------------------------------- Car ----------------------------------------------------
    Car getCar(Long id) throws EntityNotFoundException;

    CarResponse getCarResponse(Long id) throws EntityNotFoundException;

    List<CarResponse> getAll();

    List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    List<CarResponse> getAllWithoutAdByOwner(Long ownerId) throws EntityNotFoundException;

    CarResponse create(CarRequest car, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException, AdException;

    void checkCarOwner(Car car, Long ownerId) throws PrivilegeException;

    // ------------------------------- Technical Parameters -------------------------------------

    TechnicalParametersResponse getTechnicalParameters(Long carId) throws EntityNotFoundException;

    TechnicalParametersResponse updateTechnicalParameters(TechnicalParametersRequest technicalParametersRequest,
                                                          Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException;

    // ------------------------------- General Parameters ---------------------------------------

    GeneralParametersResponse getGeneralParameters(Long carId) throws EntityNotFoundException;

    GeneralParametersResponse updateGeneralParameters(GeneralParametersRequest generalParametersRequest,
                                                      Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException;

    void checkGeneralParameters(GeneralParameters generalParameters) throws EntityNotFoundException,
            PrivilegeException;

    GeneralParametersResponse uploadPhotos(FormDataMultiPart multiPart, Long ownerId, Long carId)
            throws EntityNotFoundException, PrivilegeException, AdException, IOException, DbxException;

    GeneralParametersResponse deletePhotos(CarPhotosRequest carPhotosRequest, Long ownerId, Long carId)
            throws EntityNotFoundException, PrivilegeException, AdException;

    // ------------------------------- Current Condition ----------------------------------------

    CurrentConditionResponse getCurrentCondition(Long carId) throws EntityNotFoundException;

    CurrentConditionResponse updateCurrentCondition(CurrentConditionRequest currentConditionRequest,
                                                    Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException;
}
