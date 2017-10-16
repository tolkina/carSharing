package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.DriverLicense;

import java.util.Collection;

public interface DriverLicenseService {
    boolean isExist(Long driverLicenseId);

    DriverLicense get(Long id) throws EntityNotFoundException;

    Collection<DriverLicense> getAll();

    void create(DriverLicense driverLicense, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long driverLicenseId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
