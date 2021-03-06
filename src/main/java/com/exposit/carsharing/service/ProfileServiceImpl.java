package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.domain.AccountStatus;
import com.exposit.carsharing.domain.ConfirmProfile;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.PasswordRequest;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PasswordException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.ProfileRepository;
import com.exposit.carsharing.repository.RoleRepository;
import com.exposit.carsharing.util.AttachmentManager;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ProfileServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper,
                              CloudStorageClient cloudStorageClient, RoleRepository roleRepository,
                              BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.cloudStorageClient = cloudStorageClient;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
    public UserResponse findByEmailAndEnabledStatus(String email) throws EntityNotFoundException {
        Profile profile = profileRepository.findByEmailAndStatus(email, AccountStatus.ENABLED);
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

    @Override
    public void disableUser(Long id) throws EntityNotFoundException, PrivilegeException {
        Profile profile = getProfile(id);
        profile.setStatus(AccountStatus.DISABLED);
    }

    @Override
    public boolean isEnabled(Long id) throws EntityNotFoundException, PrivilegeException {
        return getProfile(id).getStatus() == AccountStatus.ENABLED;
    }

    @Override
    public void changePassword(Long principalId, PasswordRequest passwordRequest)
            throws EntityNotFoundException, PasswordException {
        Profile profile = getProfile(principalId);
        if (!bCryptPasswordEncoder.matches(passwordRequest.getOldPassword(), profile.getPassword())) {
            throw new PasswordException("Incorrect old password.");
        }
        profile.setPassword(bCryptPasswordEncoder.encode(passwordRequest.getPassword()));
    }

    private ProfileResponse mapToResponse(Profile profile) {
        return modelMapper.map(profile, ProfileResponse.class);
    }
}
