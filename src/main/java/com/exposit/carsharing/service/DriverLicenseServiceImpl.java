package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
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
    public boolean isExist(Long driverLicenseId) {
        return driverLicenseRepository.findOne(driverLicenseId) != null;
    }

    @Override
    public DriverLicense get(Long id) throws EntityNotFoundException {
        DriverLicense driverLicense = driverLicenseRepository.findOne(id);
        if (driverLicense == null) {
            throw new EntityNotFoundException("Driver license", id);
        }
        return driverLicense;
    }

    @Override
    public Collection<DriverLicense> getAll() {
        return driverLicenseRepository.findAll();
    }

    @Override
    public void create(DriverLicense driverLicense, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (driverLicense.getId() != null && isExist(driverLicense.getId())) {
            throw new EntityAlreadyExistException("Driver license", driverLicense.getId());
        }
        driverLicense.setOwner(profileService.get(ownerId));
        driverLicenseRepository.save(driverLicense);
    }

    @Override
    public void delete(Long driverLicenseId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(driverLicenseId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        driverLicenseRepository.delete(driverLicenseId);
    }
}
