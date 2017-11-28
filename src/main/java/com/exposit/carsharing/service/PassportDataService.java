package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;

public interface PassportDataService {
    PassportDataResponse getPassportDataResponse(Long ownerId) throws EntityNotFoundException;

    PassportDataResponse updatePassportData(Long ownerId, PassportDataRequest passportDataRequest)
            throws EntityNotFoundException;

    PassportDataResponse uploadRegistrationPhoto(Long ownerId, InputStream inputStream,
                                                 FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException;

    PassportDataResponse uploadPhoto(Long ownerId, InputStream inputStream, FormDataContentDisposition fileDetail)
            throws EntityNotFoundException, IOException, DbxException;

    PassportDataResponse deletePhoto(Long ownerId) throws EntityNotFoundException;

    PassportDataResponse deleteRegistrationPhoto(Long ownerId) throws EntityNotFoundException;
}
