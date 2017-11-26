package com.exposit.carsharing.service;

import com.dropbox.core.DbxException;
import com.exposit.carsharing.cloud.CloudStorageClient;
import com.exposit.carsharing.domain.*;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.AdException;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.CarRepository;
import com.exposit.carsharing.repository.CurrentConditionRepository;
import com.exposit.carsharing.repository.GeneralParametersRepository;
import com.exposit.carsharing.repository.TechnicalParametersRepository;
import com.exposit.carsharing.util.AttachmentManager;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;
    private final CurrentConditionRepository currentConditionRepository;
    private final GeneralParametersRepository generalParametersRepository;
    private final TechnicalParametersRepository technicalParametersRepository;
    private final AdminService adminService;
    private final CloudStorageClient cloudStorageClient;

    public CarServiceImpl(CarRepository carRepository,
                          ProfileService profileService,
                          ModelMapper modelMapper,
                          CurrentConditionRepository currentConditionRepository,
                          GeneralParametersRepository generalParametersRepository,
                          TechnicalParametersRepository technicalParametersRepository,
                          AdminService adminService, CloudStorageClient cloudStorageClient) {
        this.carRepository = carRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.currentConditionRepository = currentConditionRepository;
        this.generalParametersRepository = generalParametersRepository;
        this.technicalParametersRepository = technicalParametersRepository;
        this.adminService = adminService;
        this.cloudStorageClient = cloudStorageClient;
    }

    // ------------------------------- Car ----------------------------------------------------

    @Override
    public Car getCar(Long id) throws EntityNotFoundException {
        Car car = carRepository.findOne(id);
        if (car == null) {
            throw new EntityNotFoundException("Car", id);
        }
        return car;
    }

    @Override
    public CarResponse getCarResponse(Long id) throws EntityNotFoundException {
        return mapToResponse(getCar(id));
    }

    @Override
    public List<CarResponse> getAll() {
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAll().forEach(car -> cars.add(mapToResponse(car)));
        return cars;
    }

    @Override
    public List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        List<CarResponse> cars = new ArrayList<>();
        profileService.getProfile(ownerId).getCars().forEach(car -> cars.add(mapToResponse(car)));
        return cars;
    }

    @Override
    public List<CarResponse> getAllWithoutAdByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.getProfile(ownerId);
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAllByOwnerAndAd(owner, null).forEach(car -> cars.add(mapToResponse(car)));
        return cars;
    }

    @Override
    public CarResponse create(CarRequest carRequest, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = new Car();
        car.setOwner(profileService.getProfile(ownerId));
        carRepository.save(car);
        CurrentCondition currentCondition = new CurrentCondition();
        if (carRequest.getCurrentCondition() != null) {
            currentCondition = modelMapper.map(carRequest.getCurrentCondition(), CurrentCondition.class);
        }
        GeneralParameters generalParameters = new GeneralParameters();
        if (carRequest.getGeneralParameters() != null) {
            generalParameters = modelMapper.map(carRequest.getGeneralParameters(), GeneralParameters.class);
        }
        TechnicalParameters technicalParameters = new TechnicalParameters();
        if (carRequest.getTechnicalParameters() != null) {
            technicalParameters = modelMapper.map(carRequest.getTechnicalParameters(), TechnicalParameters.class);
        }
        checkGeneralParameters(generalParameters);
        checkTechnicalParameters(technicalParameters);
        currentCondition.setCar(car);
        generalParameters.setCar(car);
        technicalParameters.setCar(car);
        currentConditionRepository.save(currentCondition);
        generalParametersRepository.save(generalParameters);
        technicalParametersRepository.save(technicalParameters);
        car.setCurrentCondition(currentCondition);
        car.setGeneralParameters(generalParameters);
        car.setTechnicalParameters(technicalParameters);
        return mapToResponse(car);
    }

    @Override
    public void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException, AdException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        carRepository.delete(carId);
    }

    @Override
    public void checkCarOwner(Car car, Long ownerId) throws PrivilegeException {
        if (!car.getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
    }

    private CarResponse mapToResponse(Car car) {
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setOwner(modelMapper.map(car.getOwner(), ProfileResponse.class));
        carResponse.setCurrentCondition(mapToResponse(car.getCurrentCondition()));
        carResponse.setGeneralParameters(mapToResponse(car.getGeneralParameters()));
        carResponse.setTechnicalParameters(mapToResponse(car.getTechnicalParameters()));
        return carResponse;
    }

    private void checkCarTaken(Car car) throws AdException {
        if (car.getAd() != null && car.getAd().getStatus() == AdStatus.TAKEN) {
            throw new AdException(String.format(
                    "Can't perform the action. The ad for this car has status %s", AdStatus.TAKEN));
        }
    }

    // ------------------------------- Technical Parameters -------------------------------------

    @Override
    public TechnicalParametersResponse getTechnicalParameters(Long carId) throws EntityNotFoundException {
        return mapToResponse(getCar(carId).getTechnicalParameters());
    }

    @Override
    public TechnicalParametersResponse updateTechnicalParameters(TechnicalParametersRequest technicalParametersRequest,
                                                                 Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        TechnicalParameters technicalParameters = modelMapper.map(technicalParametersRequest, TechnicalParameters.class);
        checkTechnicalParameters(technicalParameters);
        technicalParameters.setId(car.getTechnicalParameters().getId());
        technicalParameters.setCar(car);
        technicalParametersRepository.save(technicalParameters);
        return mapToResponse(technicalParameters);
    }

    private void checkTechnicalParameters(TechnicalParameters technicalParameters)
            throws EntityAlreadyExistException, EntityNotFoundException {
        adminService.checkBodyTypeExist(technicalParameters.getBodyType());
        adminService.checkColorExist(technicalParameters.getColor());
        adminService.checkGearboxExist(technicalParameters.getGearbox());
        adminService.checkFuelTypeExist(technicalParameters.getFuelType());
        adminService.checkInteriorMaterialExist(technicalParameters.getInteriorMaterial());
        adminService.checkTiresSeasonExist(technicalParameters.getTiresSeason());
        adminService.checkDriveUnitExist(technicalParameters.getDriveUnit());
    }

    private TechnicalParametersResponse mapToResponse(TechnicalParameters technicalParameters) {
        return modelMapper.map(technicalParameters, TechnicalParametersResponse.class);
    }

    // ------------------------------- General Parameters ---------------------------------------

    @Override
    public GeneralParametersResponse getGeneralParameters(Long carId) throws EntityNotFoundException {
        return mapToResponse(getCar(carId).getGeneralParameters());
    }

    @Override
    public GeneralParametersResponse updateGeneralParameters(GeneralParametersRequest generalParametersRequest,
                                                             Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        GeneralParameters generalParameters = car.getGeneralParameters();
        generalParameters.setYearOfIssue(generalParametersRequest.getYearOfIssue());
        return mapToResponse(generalParameters);
    }

    @Override
    public void checkGeneralParameters(GeneralParameters generalParameters)
            throws EntityNotFoundException, PrivilegeException {
        adminService.checkBrandAndModelExist(generalParameters.getBrand(), generalParameters.getModel());
    }

    @Override
    public GeneralParametersResponse uploadPhotos(FormDataMultiPart multiPart, Long ownerId, Long carId)
            throws EntityNotFoundException, PrivilegeException, AdException, IOException, DbxException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        for (FormDataBodyPart bodyPart : multiPart.getFields("files")) {
            String sharedUrl = uploadCarPhoto(bodyPart, ownerId, carId);
            car.getGeneralParameters().getPhotos().add(sharedUrl);
        }
        return mapToResponse(car.getGeneralParameters());
    }

    @Override
    public GeneralParametersResponse deletePhotos(CarPhotosRequest carPhotosRequest, Long ownerId, Long carId)
            throws EntityNotFoundException, PrivilegeException, AdException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        System.out.println(carPhotosRequest.getPhotos());
        carPhotosRequest.getPhotos().forEach(photo -> car.getGeneralParameters().getPhotos().remove(photo));
        return mapToResponse(car.getGeneralParameters());
    }

    private GeneralParametersResponse mapToResponse(GeneralParameters generalParameters) {
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }

    private String uploadCarPhoto(FormDataBodyPart bodyPart, Long ownerId, Long carId)
            throws IOException, DbxException {
        BodyPartEntity bodyPartEntity = (BodyPartEntity) bodyPart.getEntity();
        String fileName = bodyPart.getContentDisposition().getFileName();
        AttachmentManager.checkFileExtension(AttachmentManager.getFileExtension(fileName));
        String pathToSave = String.format("/profile/%d/car/%d/%s", ownerId, carId, fileName);
        cloudStorageClient.uploadFile(bodyPartEntity.getInputStream(), pathToSave);
        return cloudStorageClient.createSharedLink(pathToSave);
    }

    // ------------------------------- Current Condition ----------------------------------------

    @Override
    public CurrentConditionResponse getCurrentCondition(Long carId) throws EntityNotFoundException {
        return mapToResponse(getCar(carId).getCurrentCondition());
    }

    @Override
    public CurrentConditionResponse updateCurrentCondition(
            CurrentConditionRequest currentConditionRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException, AdException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        checkCarTaken(car);
        CurrentCondition currentCondition = car.getCurrentCondition();
        currentCondition.setDamageDescription(currentConditionRequest.getDamageDescription());
        currentCondition.setMileage(currentConditionRequest.getMileage());
        return mapToResponse(currentCondition);
    }

    private CurrentConditionResponse mapToResponse(CurrentCondition currentCondition) {
        return modelMapper.map(currentCondition, CurrentConditionResponse.class);
    }
}
