package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.DriverLicense;
import com.exposit.carsharing.repository.DriverLicenseRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final ProfileService profileService;

    public DriverLicenseServiceImpl(DriverLicenseRepository driverLicenseRepository, ProfileService profileService) {
        this.driverLicenseRepository = driverLicenseRepository;
        this.profileService = profileService;
    }

    @Override
    public DriverLicense getDriverLicense(Long id) {
        return driverLicenseRepository.findOne(id);
    }

    @Override
    public Collection<DriverLicense> getAllDriverLicenses() {
        return driverLicenseRepository.findAll();
    }

    @Override
    public void createDriverLicense(DriverLicense driverLicense, Long ownerId) throws EntityNotFoundException {
        driverLicense.setOwner(profileService.getProfile(ownerId));
        driverLicenseRepository.save(driverLicense);
    }
}
