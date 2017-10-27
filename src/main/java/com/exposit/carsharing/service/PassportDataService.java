package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.PassportData;

import java.util.Collection;

public interface PassportDataService {
    boolean isExist(Long passportDataId);

    PassportDataResponse getPassportResponse(Long id) throws EntityNotFoundException;
    PassportData getPassport(Long id) throws EntityNotFoundException;
    PassportData get(Long id) throws EntityNotFoundException;

    PassportDataResponse updatePassport(Long id, PassportDataRequest passportDataRequest) throws EntityNotFoundException;
    Collection<PassportData> getAll();

    PassportDataResponse createPassport (Long ownerId, PassportDataRequest passportDataRequest) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long passportDataId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
