package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Profile;

import java.util.Collection;
import java.util.List;

public interface ProfileService {

    boolean isExist(Long id);

    boolean isEmailUsed(String email);

    Profile getProfile(Long id) throws EntityNotFoundException;

    List<Profile> getAllProfiles();

    void createProfile(Profile profile) throws EntityAlreadyExistException;
}
