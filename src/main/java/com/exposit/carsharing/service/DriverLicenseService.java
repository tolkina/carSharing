package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import java.io.IOException;
import java.io.InputStream;

public interface DriverLicenseService {
    DriverLicenseResponse getDriverLicenseResponse(Long ownerId) throws EntityNotFoundException;

    DriverLicenseResponse updateDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException;

    DriverLicenseResponse uploadFrontSidePhoto(Long ownerId, InputStream inputStream,
                                               FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException;

    DriverLicenseResponse uploadBackSidePhoto(Long ownerId, InputStream inputStream,
                                              FormDataContentDisposition fileDetail)
            throws EntityNotFoundException, IOException, DbxException;

    DriverLicenseResponse deleteFrontSidePhoto(Long ownerId) throws EntityNotFoundException;

    DriverLicenseResponse deleteBackSidePhoto(Long ownerId) throws EntityNotFoundException;
}
