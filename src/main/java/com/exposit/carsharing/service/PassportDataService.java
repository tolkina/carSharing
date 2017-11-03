package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;

import java.util.Collection;

public interface PassportDataService {
    PassportDataResponse getPassportDataResponse(Long ownerId) throws EntityNotFoundException;

    PassportDataResponse updatePassportData(Long ownerId, PassportDataRequest passportDataRequest) throws EntityNotFoundException;

    Collection<PassportDataResponse> getAll();
}
