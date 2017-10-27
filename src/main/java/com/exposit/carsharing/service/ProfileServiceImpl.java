package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ModelMapper modelMapper;
    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isExist(Long id) {
        return profileRepository.findOne(id) != null;
    }

    @Override
    public boolean isEmailUsed(String email) {
        return profileRepository.findByEmail(email) != null;
    }

    @Override
    public Profile get(Long id) throws EntityNotFoundException {
        Profile profile = profileRepository.findOne(id);
        if (profile == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d not found", id));
        }
        return profile;
    }

    public ProfileResponse getProfileResponse(Long id) throws EntityNotFoundException {
        return modelMapper.map(getProfile(id), ProfileResponse.class);
    }

    public Profile getProfile(Long id) throws EntityNotFoundException {
        Profile profile = profileRepository.findOne(id);
        if (profile == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d not found", id));
        }
        return profile;
    }

    @Override
    public List<ProfileResponse> getAll() {
        List<ProfileResponse> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(
                profile -> profiles.add(modelMapper.map(profile, ProfileResponse.class)));
        return profiles;
    }

    /*@Override
    public void create(Profile profile) throws EntityAlreadyExistException {
        if (profile.getId() != null && isExist(profile.getId())) {
            throw new EntityAlreadyExistException(String.format("Id %d already used", profile.getId()));
        }
        if (profile.getEmail() != null && isEmailUsed(profile.getEmail())) {
            throw new EntityAlreadyExistException(String.format("Email %s already used", profile.getEmail()));
        }
        profileRepository.save(profile);
    }*/

    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) throws EntityAlreadyExistException {
        if (profileRequest.getEmail() != null && isEmailUsed(profileRequest.getEmail())) {
            throw new EntityAlreadyExistException(String.format("Email %s already used", profileRequest.getEmail()));
        }
        Profile profile = modelMapper.map(profileRequest, Profile.class);
        profileRepository.save(profile);
        return modelMapper.map(profile, ProfileResponse.class);
    }

    @Override
    public ProfileResponse updateProfile(Long id, ProfileRequest profileRequest) throws EntityNotFoundException {
        Profile profile = getProfile(id);
        profile.setDrivingExperience(profileRequest.getDrivingExperience());
        profile.setBirthday(profileRequest.getBirthday());
        profileRepository.save(profile);
        return modelMapper.map(profile, ProfileResponse.class);
    }

    @Override
    public void delete(Long profileId) throws EntityNotFoundException {
        profileRepository.delete(get(profileId));
    }
}
