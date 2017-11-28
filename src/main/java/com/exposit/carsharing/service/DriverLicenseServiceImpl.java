package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.DriverLicenseRequest;
import com.exposit.carsharing.dto.DriverLicenseResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.DriverLicenseRepository;
import com.exposit.carsharing.util.AttachmentManager;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class DriverLicenseServiceImpl implements DriverLicenseService {
    private final DriverLicenseRepository driverLicenseRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;
    private final CloudStorageClient cloudStorageClient;

    public DriverLicenseServiceImpl(DriverLicenseRepository driverLicenseRepository,
                                    ProfileService profileService,
                                    ModelMapper modelMapper, CloudStorageClient cloudStorageClient) {
        this.driverLicenseRepository = driverLicenseRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.cloudStorageClient = cloudStorageClient;
    }

    @Override
    public DriverLicenseResponse getDriverLicenseResponse(Long ownerId) throws EntityNotFoundException {
        return mapToResponse(getDriverLicense(ownerId));
    }

    @Override
    public DriverLicenseResponse updateDriverLicense(Long ownerId, DriverLicenseRequest driverLicenseRequest)
            throws EntityNotFoundException {
        DriverLicense driverLicenseOld = getDriverLicense(ownerId);
        DriverLicense driverLicenseNew = mapFromRequest(driverLicenseRequest);
        driverLicenseNew.setId(driverLicenseOld.getId());
        driverLicenseNew.setOwner(driverLicenseOld.getOwner());
        driverLicenseRepository.save(driverLicenseNew);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(driverLicenseNew);
    }

    @Override
    public DriverLicenseResponse uploadFrontSidePhoto(Long ownerId, InputStream inputStream,
                                                      FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException {
        DriverLicense driverLicense = getDriverLicense(ownerId);
        String sharedUrl = uploadDriverLicensePhoto(ownerId, inputStream, fileDetail, "front-side");
        driverLicense.setFrontSideImageUrl(sharedUrl);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(driverLicense);
    }

    @Override
    public DriverLicenseResponse uploadBackSidePhoto(Long ownerId, InputStream inputStream,
                                                     FormDataContentDisposition fileDetail)
            throws EntityNotFoundException, IOException, DbxException {
        DriverLicense driverLicense = getDriverLicense(ownerId);
        String sharedUrl = uploadDriverLicensePhoto(ownerId, inputStream, fileDetail, "back-side");
        driverLicense.setBackSideImageUrl(sharedUrl);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(driverLicense);
    }

    @Override
    public DriverLicenseResponse deleteFrontSidePhoto(Long ownerId) throws EntityNotFoundException {
        DriverLicense driverLicense = getDriverLicense(ownerId);
        driverLicense.setFrontSideImageUrl(null);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(driverLicense);
    }

    @Override
    public DriverLicenseResponse deleteBackSidePhoto(Long ownerId) throws EntityNotFoundException {
        DriverLicense driverLicense = getDriverLicense(ownerId);
        driverLicense.setBackSideImageUrl(null);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(driverLicense);
    }

    private DriverLicense getDriverLicense(Long ownerId) throws EntityNotFoundException {
        Profile profile = profileService.getProfile(ownerId);
        DriverLicense driverLicense = driverLicenseRepository.findByOwner(profile);
        if (driverLicense == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d don't have driver license", ownerId));
        }
        return driverLicense;
    }

    private DriverLicenseResponse mapToResponse(DriverLicense driverLicense) {
        return modelMapper.map(driverLicense, DriverLicenseResponse.class);
    }

    private DriverLicense mapFromRequest(DriverLicenseRequest driverLicenseRequest) {
        return modelMapper.map(driverLicenseRequest, DriverLicense.class);
    }

    private String uploadDriverLicensePhoto(Long ownerId, InputStream uploadedInputStream,
                                            FormDataContentDisposition fileDetail, String photoType)
            throws IOException, DbxException {
        AttachmentManager.checkFormData(uploadedInputStream, fileDetail);
        String fileName = fileDetail.getFileName();
        AttachmentManager.checkFileExtension(AttachmentManager.getFileExtension(fileName));
        String pathToSave = String.format("/profile/%d/driver-license/%s/%s", ownerId, photoType, fileName);
        cloudStorageClient.uploadFile(uploadedInputStream, pathToSave);
        return cloudStorageClient.createSharedLink(pathToSave);
    }
}
