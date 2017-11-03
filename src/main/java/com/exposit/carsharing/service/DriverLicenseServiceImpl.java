package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.DriverLicenseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public DriverLicenseServiceImpl(DriverLicenseRepository driverLicenseRepository,
                                    ProfileService profileService,
                                    ModelMapper modelMapper) {
        this.driverLicenseRepository = driverLicenseRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DriverLicenseResponse getDriverLicenseResponse(Long ownerId) throws EntityNotFoundException {
        return mapToResponse(getDriverLicense(ownerId));
    }

    @Override
    public Collection<DriverLicenseResponse> getAll() {
        List<DriverLicenseResponse> driverLicenses = new ArrayList<>();
        driverLicenseRepository.findAll().forEach(driverLicense -> driverLicenses.add(mapToResponse(driverLicense)));
        return driverLicenses;
    }

    @Override
    public DriverLicenseResponse updateDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException {
        DriverLicense driverLicenseOld = getDriverLicense(ownerId);
        DriverLicense driverLicenseNew = mapFromRequest(driverLicenseRequest);
        driverLicenseNew.setId(driverLicenseOld.getId());
        driverLicenseNew.setOwner(driverLicenseOld.getOwner());
        driverLicenseRepository.save(driverLicenseNew);
        return mapToResponse(driverLicenseNew);
    }

    private DriverLicense getDriverLicense(Long ownerId) throws EntityNotFoundException {
        Profile profile = profileService.getProfile(ownerId);
        DriverLicense driverLicense = driverLicenseRepository.findByOwner(profile);
        if (driverLicense == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d don't have driver license", ownerId));
        }
        return driverLicense;
    }

    private DriverLicenseResponse mapToResponse(DriverLicense driverLicense) {
        return modelMapper.map(driverLicense, DriverLicenseResponse.class);
    }

    private DriverLicense mapFromRequest(DriverLicenseRequest driverLicenseRequest) {
        return modelMapper.map(driverLicenseRequest, DriverLicense.class);
    }
}
