package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;

import java.util.Collection;

public interface DriverLicenseService {
    DriverLicenseResponse getDriverLicenseResponse(Long ownerId) throws EntityNotFoundException;

    DriverLicenseResponse updateDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException;

    Collection<DriverLicenseResponse> getAll();
}
