package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;

import java.util.List;

public interface ProfileService {
    boolean isExist(Long id);

    boolean isEmailUsed(String email);

    Profile get(Long id) throws EntityNotFoundException;

    Profile updateProfile(Profile profile);

    List<ProfileResponse> getAll();

    void create(Profile profile) throws EntityAlreadyExistException;

    void delete(Long profileId) throws EntityNotFoundException;
}
