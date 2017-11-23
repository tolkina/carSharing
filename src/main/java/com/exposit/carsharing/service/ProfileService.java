package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.ProfileRequest;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ProfileService {
    Profile getProfile(Long id) throws EntityNotFoundException;

    ProfileResponse getProfileResponse(Long id) throws EntityNotFoundException;

    ProfileResponse updateProfile(Long id, ProfileRequest profileRequest) throws EntityNotFoundException;

    List<ProfileResponse> getAll();

    void delete(Long profileId) throws EntityNotFoundException;

    UserResponse findByEmail(String email) throws EntityNotFoundException;

    void setConfirmProfileCheck(Long profileId) throws EntityNotFoundException, ConfirmProfileException;

    void setConfirmProfileNo(Long profileId) throws EntityNotFoundException;

    ProfileResponse uploadUserAvatar(Long id, InputStream uploadedInputStream, FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException;
}
