package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.repository.DriverLicenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public DriverLicenseServiceImpl(DriverLicenseRepository driverLicenseRepository, ProfileService profileService, ModelMapper modelMapper) {
        this.driverLicenseRepository = driverLicenseRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
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
    public DriverLicenseResponse getDriverLicenseResponse(Long id) throws EntityNotFoundException {
        Profile profile = profileService.get(id);
        DriverLicense driverLicense = driverLicenseRepository.findByOwner(profile);
        if (driverLicense == null) {
            throw new EntityNotFoundException("Driver license", id);
        }
        return modelMapper.map(driverLicense, DriverLicenseResponse.class);
    }

    @Override
    public DriverLicense getDriverLicense(Long id) throws EntityNotFoundException {
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

    /*@Override
    public void create(DriverLicense driverLicense, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (driverLicense.getId() != null && isExist(driverLicense.getId())) {
            throw new EntityAlreadyExistException("Driver license", driverLicense.getId());
        }
        driverLicense.setOwner(profileService.getAd(ownerId));
        driverLicenseRepository.save(driverLicense);
    }*/

    @Override
    public DriverLicenseResponse createDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        DriverLicense driverLicense = modelMapper.map(driverLicenseRequest, DriverLicense.class);
        driverLicense.setOwner(profileService.get(ownerId));
        driverLicenseRepository.save(driverLicense);
        return modelMapper.map(driverLicense, DriverLicenseResponse.class);
    }

    @Override
    public DriverLicenseResponse updateDriverLicense (Long id, DriverLicenseResponse driverLicenseResponse) throws EntityNotFoundException {

        DriverLicense driverLicense = getDriverLicense(id);
        driverLicense.setOwner(profileService.get(id));
        driverLicense.setSeriesAndNumber(driverLicenseResponse.getSeriesAndNumber());
        driverLicense.setCategory(driverLicenseResponse.getCategory());
        return modelMapper.map(driverLicense, DriverLicenseResponse.class);
    }

    @Override
    public void delete(Long id) throws PrivilegeException, EntityNotFoundException {
        driverLicenseRepository.delete(id);
    }
}
