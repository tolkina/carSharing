package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.repository.PassportDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class PassportDataServiceImpl implements PassportDataService {
    private final PassportDataRepository passportDataRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public PassportDataServiceImpl(PassportDataRepository passportDataRepository, ProfileService profileService, ModelMapper modelMapper) {
        this.passportDataRepository = passportDataRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
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
    public PassportDataResponse getPassportResponse(Long id) throws EntityNotFoundException {
        PassportData passportData = passportDataRepository.findOne(id);
        if (passportData == null) {
            throw new EntityNotFoundException("Passport Data", id);
        }
        return modelMapper.map(passportData, PassportDataResponse.class);
    }

    @Override
    public PassportData getPassport(Long id) throws EntityNotFoundException {
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

    /*@Override
    public void create(PassportData passportData, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (passportData.getId() != null && isExist(passportData.getId())) {
            throw new EntityAlreadyExistException("Passport data", passportData.getId());
        }
        passportData.setOwner(profileService.get(ownerId));
        passportDataRepository.save(passportData);
    }*/
    @Override
    public PassportDataResponse createPassport(Long ownerId, PassportDataRequest passportDataRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        PassportData passportData = modelMapper.map(passportDataRequest, PassportData.class);
        passportData.setOwner(profileService.get(ownerId));
        passportDataRepository.save(passportData);
        return modelMapper.map(passportData, PassportDataResponse.class);
    }


    @Override
    public PassportDataResponse updatePassport(Long id, PassportDataRequest passportDataRequest) throws EntityNotFoundException {
        PassportData passportData = getPassport(id);
        passportData.setOwner(profileService.get(id));
        passportData.setSeries(passportDataRequest.getSeries());
        passportData.setDateOfIssue(passportDataRequest.getDateOfIssue());
        passportData.setFirstName(passportDataRequest.getFirstName());
        passportData.setLastName(passportDataRequest.getLastName());
        passportData.setMiddleName(passportDataRequest.getMiddleName());
        passportData.setNumber(passportDataRequest.getNumber());
        passportData.setPersonalNumber(passportData.getPersonalNumber());
        passportData.setPlaceOfIssue(passportDataRequest.getPlaceOfIssue());
        passportData.setValidUntil(passportDataRequest.getValidUntil());
        passportDataRepository.save(passportData);
        return modelMapper.map(passportData, PassportDataResponse.class);
    }

    @Override
    public void delete(Long passportDataId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(passportDataId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        passportDataRepository.delete(passportDataId);
    }
}
