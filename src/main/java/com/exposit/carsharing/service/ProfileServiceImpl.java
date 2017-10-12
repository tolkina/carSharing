package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Profile;
import com.exposit.carsharing.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final
    ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
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
    public Profile getProfile(Long id) throws EntityNotFoundException {
        Profile profile = profileRepository.findOne(id);
        if (profile == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d not found", id));
        }
        return profile;
    }

    @Override
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public void createProfile(Profile profile) throws EntityAlreadyExistException {
        if (profile.getId() != null && isExist(profile.getId())) {
            throw new EntityAlreadyExistException(String.format("Id %d already used", profile.getId()));
        }
        if (profile.getEmail() != null && isEmailUsed(profile.getEmail())) {
            throw new EntityAlreadyExistException(String.format("Email %s already used", profile.getEmail()));
        }
        profileRepository.save(profile);
    }
}
