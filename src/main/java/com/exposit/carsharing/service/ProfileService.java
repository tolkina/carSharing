package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Profile;

import java.util.List;

public interface ProfileService {
    boolean isExist(Long id);

    boolean isEmailUsed(String email);

    Profile get(Long id) throws EntityNotFoundException;

<<<<<<< HEAD
    Profile updateProfile(Profile profile);

    List<Profile> getAll();
=======
    List<ProfileResponse> getAll();
>>>>>>> feature/admin-panel

    void create(Profile profile) throws EntityAlreadyExistException;

    void delete(Long profileId) throws EntityNotFoundException;
}
