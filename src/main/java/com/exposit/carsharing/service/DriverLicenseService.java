package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.DriverLicense;

import java.util.Collection;

public interface DriverLicenseService {
    boolean isExist(Long driverLicenseId);

    DriverLicenseResponse getDriverLicenseResponse(Long id) throws EntityNotFoundException;
    DriverLicense getDriverLicense(Long id) throws EntityNotFoundException;
    DriverLicense get(Long id) throws EntityNotFoundException;

    DriverLicenseResponse updateDriverLicense(Long id, DriverLicenseResponse driverLicenseResponse) throws EntityNotFoundException;
    Collection<DriverLicense> getAll();

    DriverLicenseResponse createDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long id) throws PrivilegeException, EntityNotFoundException;
}
