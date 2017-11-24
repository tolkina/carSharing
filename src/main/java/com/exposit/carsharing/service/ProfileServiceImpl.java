package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.domain.ConfirmProfile;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.ProfileRepository;
import com.exposit.carsharing.util.AttachmentManager;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    private final ModelMapper modelMapper;
    private final ProfileRepository profileRepository;
    private final CloudStorageClient cloudStorageClient;

    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper,
                              CloudStorageClient cloudStorageClient) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.cloudStorageClient = cloudStorageClient;
    }

    @Override
    public Profile getProfile(Long id) throws EntityNotFoundException {
        Profile profile = profileRepository.findOne(id);
        if (profile == null) {
            throw new EntityNotFoundException("Profile", id);
        }
        return profile;
    }

    public ProfileResponse getProfileResponse(Long id) throws EntityNotFoundException {
        return mapToResponse(getProfile(id));
    }

    @Override
    public List<ProfileResponse> getAll() {
        List<ProfileResponse> profiles = new ArrayList<>();
        profileRepository.findAll().forEach(profile -> profiles.add(mapToResponse(profile)));
        return profiles;
    }

    @Override
    public ProfileResponse updateProfile(Long id, @Valid ProfileRequest profileRequest) throws EntityNotFoundException {
        Profile profile = getProfile(id);
        profile.setDrivingExperience(profileRequest.getDrivingExperience());
        profile.setBirthday(profileRequest.getBirthday());
        profile.setLogin(profileRequest.getLogin());
        profileRepository.save(profile);
        return mapToResponse(profile);
    }

    @Override
    public void delete(Long profileId) throws EntityNotFoundException {
        profileRepository.delete(this.getProfile(profileId));
    }

    @Override
    public UserResponse findByEmail(String email) throws EntityNotFoundException {
        Profile profile = profileRepository.findByEmail(email);
        if (profile == null) {
            throw new EntityNotFoundException("Profile not found.");
        }
        return modelMapper.map(profile, UserResponse.class);
    }

    @Override
    public void setConfirmProfileCheck(Long profileId) throws EntityNotFoundException, ConfirmProfileException {
        Profile profile = getProfile(profileId);
        if (profile.getConfirmProfile().equals(ConfirmProfile.YES)) {
            throw new ConfirmProfileException("This profile has been already confirmed.");
        }
        profile.setConfirmProfile(ConfirmProfile.CHECK);
        mapToResponse(profile);
    }

    @Override
    public void setConfirmProfileNo(Long profileId) throws EntityNotFoundException {
        Profile profile = getProfile(profileId);
        profile.setConfirmProfile(ConfirmProfile.NO);
    }

    @Override
    public ProfileResponse uploadProfileAvatar(Long id, InputStream uploadedInputStream,
                                               FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException {
        AttachmentManager.checkFormData(uploadedInputStream, fileDetail);
        String fileName = fileDetail.getFileName();
        AttachmentManager.checkFileExtension(AttachmentManager.getFileExtension(fileName));
        String pathToSave = String.format("/profile/%d/avatar/%s", id, fileName);
        cloudStorageClient.uploadFile(uploadedInputStream, pathToSave);
        String sharedUrl = cloudStorageClient.createSharedLink(pathToSave);
        Profile profile = getProfile(id);
        profile.setAvatarUrl(sharedUrl);
        return mapToResponse(profile);
    }

    @Override
    public ProfileResponse deleteProfileAvatar(Long id) throws EntityNotFoundException {
        Profile profile = getProfile(id);
        profile.setAvatarUrl(null);
        return mapToResponse(profile);
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return modelMapper.map(profile, ProfileResponse.class);
    }

    private boolean existAvatar(Profile profile) {
        return profile.getAvatarUrl() != null;
    }
}
