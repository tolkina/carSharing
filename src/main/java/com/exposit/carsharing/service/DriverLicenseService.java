package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.DriverLicense;

import java.util.Collection;

public interface DriverLicenseService {

    DriverLicense getDriverLicense(Long id);

    Collection<DriverLicense> getAllDriverLicenses();

    void createDriverLicense(DriverLicense driverLicense, Long ownerId) throws EntityNotFoundException;
}
