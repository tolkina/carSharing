package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.PassportData;
import com.exposit.carsharing.repository.PassportDataRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PassportDataServiceImpl implements PassportDataService {
    private final PassportDataRepository passportDataRepository;
    private final ProfileService profileService;

    public PassportDataServiceImpl(PassportDataRepository passportDataRepository, ProfileService profileService) {
        this.passportDataRepository = passportDataRepository;
        this.profileService = profileService;
    }

    @Override
    public PassportData getPassportData(Long id) {
        return passportDataRepository.findOne(id);
    }

    @Override
    public Collection<PassportData> getAllPassportData() {
        return passportDataRepository.findAll();
    }

    @Override
    public void createPassportData(PassportData passportData, Long ownerId) throws EntityNotFoundException {
        passportData.setOwner(profileService.getProfile(ownerId));
        passportDataRepository.save(passportData);
    }
}
