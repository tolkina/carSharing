package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
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
    public boolean isExist(Long passportDataId) {
        return passportDataRepository.findOne(passportDataId) != null;
    }

    @Override
    public PassportData get(Long id) throws EntityNotFoundException {
        PassportData passportData = passportDataRepository.findOne(id);
        if (passportData == null) {
            throw new EntityNotFoundException("Passport Data", id);
        }
        return passportData;
    }

    @Override
    public Collection<PassportData> getAll() {
        return passportDataRepository.findAll();
    }

    @Override
    public void create(PassportData passportData, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (passportData.getId() != null && isExist(passportData.getId())) {
            throw new EntityAlreadyExistException("Passport data", passportData.getId());
        }
        passportData.setOwner(profileService.get(ownerId));
        passportDataRepository.save(passportData);
    }

    @Override
    public void delete(Long passportDataId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(passportDataId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        passportDataRepository.delete(passportDataId);
    }
}
