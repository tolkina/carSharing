package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;

import java.util.List;

public interface ProfileService {
    boolean isExist(Long id);

    boolean isEmailUsed(String email);

    Profile get(Long id) throws EntityNotFoundException;
    ProfileResponse getProfileResponse(Long id) throws EntityNotFoundException;

    ProfileResponse updateProfile(Long id, ProfileRequest profileRequest) throws EntityNotFoundException;

    List<ProfileResponse> getAll();

    ProfileResponse createProfile (ProfileRequest profileRequest) throws EntityAlreadyExistException;

    void delete(Long profileId) throws EntityNotFoundException;
}
