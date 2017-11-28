package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.PassportDataRequest;
import com.exposit.carsharing.dto.PassportDataResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.PassportDataRepository;
import com.exposit.carsharing.util.AttachmentManager;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

@Service
@Transactional
public class PassportDataServiceImpl implements PassportDataService {
    private final PassportDataRepository passportDataRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;
    private final CloudStorageClient cloudStorageClient;

    public PassportDataServiceImpl(PassportDataRepository passportDataRepository,
                                   ProfileService profileService,
                                   ModelMapper modelMapper, CloudStorageClient cloudStorageClient) {
        this.passportDataRepository = passportDataRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.cloudStorageClient = cloudStorageClient;
    }

    @Override
    public PassportDataResponse getPassportDataResponse(Long ownerId) throws EntityNotFoundException {
        return mapToResponse(getPassportData(ownerId));
    }

    @Override
    public PassportDataResponse updatePassportData(Long ownerId, PassportDataRequest passportDataRequest)
            throws EntityNotFoundException {
        PassportData passportDataOld = getPassportData(ownerId);
        PassportData passportDataNew = mapFromRequest(passportDataRequest);
        passportDataNew.setOwner(passportDataOld.getOwner());
        passportDataNew.setId(passportDataOld.getId());
        passportDataRepository.save(passportDataNew);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(passportDataNew);
    }

    @Override
    public PassportDataResponse uploadRegistrationPhoto(Long ownerId, InputStream inputStream,
                                                        FormDataContentDisposition fileDetail)
            throws IOException, DbxException, EntityNotFoundException {
        PassportData passportData = getPassportData(ownerId);
        String sharedUrl = uploadPassportDataPhoto(ownerId, inputStream, fileDetail, "registrationPhoto");
        passportData.setRegistrationPhotoUrl(sharedUrl);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(passportData);
    }

    @Override
    public PassportDataResponse uploadPhoto(Long ownerId, InputStream inputStream,
                                            FormDataContentDisposition fileDetail)
            throws EntityNotFoundException, IOException, DbxException {
        PassportData passportData = getPassportData(ownerId);
        String sharedUrl = uploadPassportDataPhoto(ownerId, inputStream, fileDetail, "photo");
        passportData.setPhotoUrl(sharedUrl);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(passportData);
    }

    @Override
    public PassportDataResponse deletePhoto(Long ownerId) throws EntityNotFoundException {
        PassportData passportData = getPassportData(ownerId);
        passportData.setPhotoUrl(null);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(passportData);
    }

    @Override
    public PassportDataResponse deleteRegistrationPhoto(Long ownerId) throws EntityNotFoundException {
        PassportData passportData = getPassportData(ownerId);
        passportData.setRegistrationPhotoUrl(null);
        profileService.setConfirmProfileNo(ownerId);
        return mapToResponse(passportData);
    }

    private PassportData getPassportData(Long ownerId) throws EntityNotFoundException {
        Profile profile = profileService.getProfile(ownerId);
        PassportData passportData = passportDataRepository.findByOwner(profile);
        if (passportData == null) {
            throw new EntityNotFoundException(String.format("Profile with id %d don't have passport data", ownerId));
        }
        return passportData;
    }

    private PassportDataResponse mapToResponse(PassportData passportData) {
        return modelMapper.map(passportData, PassportDataResponse.class);
    }

    private PassportData mapFromRequest(PassportDataRequest passportDataRequest) {
        return modelMapper.map(passportDataRequest, PassportData.class);
    }

    private String uploadPassportDataPhoto(Long ownerId, InputStream uploadedInputStream,
                                           FormDataContentDisposition fileDetail, String photoType)
            throws IOException, DbxException {
        AttachmentManager.checkFormData(uploadedInputStream, fileDetail);
        String fileName = fileDetail.getFileName();
        AttachmentManager.checkFileExtension(AttachmentManager.getFileExtension(fileName));
        String pathToSave = String.format("/profile/%d/passport/%s/%s", ownerId, photoType, fileName);
        cloudStorageClient.uploadFile(uploadedInputStream, pathToSave);
        return cloudStorageClient.createSharedLink(pathToSave);
    }
}
