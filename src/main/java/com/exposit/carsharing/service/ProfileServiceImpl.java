package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Profile;
import com.exposit.carsharing.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> feature/admin-panel
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

    @Override
    public List<ProfileResponse> getAll() {
        List<ProfileResponse> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(
                profile -> profiles.add(modelMapper.map(profile, ProfileResponse.class)));
        return profiles;
    }

    @Override
    public void create(Profile profile) throws EntityAlreadyExistException {
        if (profile.getId() != null && isExist(profile.getId())) {
            throw new EntityAlreadyExistException(String.format("Id %d already used", profile.getId()));
        }
        if (profile.getEmail() != null && isEmailUsed(profile.getEmail())) {
            throw new EntityAlreadyExistException(String.format("Email %s already used", profile.getEmail()));
        }
        profileRepository.save(profile);
    }

    public Profile updateProfile(Profile profile) {
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public void delete(Long profileId) throws EntityNotFoundException {
        profileRepository.delete(get(profileId));
    }
}
