package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.PassportDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class PassportDataServiceImpl implements PassportDataService {
    private final PassportDataRepository passportDataRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public PassportDataServiceImpl(PassportDataRepository passportDataRepository,
                                   ProfileService profileService,
                                   ModelMapper modelMapper) {
        this.passportDataRepository = passportDataRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    @Override
    public PassportDataResponse getPassportDataResponse(Long ownerId) throws EntityNotFoundException {
        return mapToResponse(getPassportData(ownerId));
    }

    @Override
    public Collection<PassportDataResponse> getAll() {
        List<PassportDataResponse> passports = new ArrayList<>();
        passportDataRepository.findAll().forEach(passport -> passports.add(mapToResponse(passport)));
        return passports;
    }

    @Override
    public PassportDataResponse updatePassportData(Long ownerId, PassportDataRequest passportDataRequest)
            throws EntityNotFoundException {
        PassportData passportDataOld = getPassportData(ownerId);
        PassportData passportDataNew = mapFromRequest(passportDataRequest);
        passportDataNew.setOwner(passportDataOld.getOwner());
        passportDataNew.setId(passportDataOld.getId());
        passportDataRepository.save(passportDataNew);
        return mapToResponse(passportDataNew);
    }

    private PassportData getPassportData(Long ownerId) throws EntityNotFoundException {
        Profile profile = profileService.getProfile(ownerId);
        PassportData passportData = passportDataRepository.findByOwner(profile);
        if (passportData == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d don't have passport data", ownerId));
        }
        return passportData;
    }

    private PassportDataResponse mapToResponse(PassportData passportData) {
        return modelMapper.map(passportData, PassportDataResponse.class);
    }

    private PassportData mapFromRequest(PassportDataRequest passportDataRequest) {
        return modelMapper.map(passportDataRequest, PassportData.class);
    }
}
