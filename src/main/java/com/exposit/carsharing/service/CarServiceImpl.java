package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.*;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.CarRepository;
import com.exposit.carsharing.repository.CurrentConditionRepository;
import com.exposit.carsharing.repository.GeneralParametersRepository;
import com.exposit.carsharing.repository.TechnicalParametersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public CarServiceImpl(CarRepository carRepository,
                          ProfileService profileService,
                          ModelMapper modelMapper,
                          CurrentConditionRepository currentConditionRepository,
                          GeneralParametersRepository generalParametersRepository,
                          TechnicalParametersRepository technicalParametersRepository,
                          AdminService adminService) {
        this.carRepository = carRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.currentConditionRepository = currentConditionRepository;
        this.generalParametersRepository = generalParametersRepository;
        this.technicalParametersRepository = technicalParametersRepository;
        this.adminService = adminService;
    }

    // ------------------------------- Car ----------------------------------------------------

    @Override
    public boolean isExist(Long id) {
        return carRepository.findOne(id) != null;
    }

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
    public CarResponse getByAd(Long adId){
        return modelMapper.map(carRepository.findByAd_Id(adId), CarResponse.class);
    }

    @Override
    public List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.getProfile(ownerId);
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAllByOwner(owner).forEach(car -> cars.add(mapToResponse(car)));
        return cars;
    }

    @Override
    public CarResponse create(CarRequest carRequest, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = new Car();
        car.setOwner(profileService.getProfile(ownerId));
        car.setAd(null);
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
    public void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        checkCarOwner(getCar(carId), ownerId);
        carRepository.delete(carId);
    }

    @Override
    public void checkCarOwner(Car car, Long ownerId) throws PrivilegeException {
        if (!car.getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
    }

    private CarResponse mapToResponse(Car car) {
        if (car == null) {
            return null;
        }
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setOwner(modelMapper.map(car.getOwner(), ProfileResponse.class));
        carResponse.setCurrentCondition(mapToResponse(car.getCurrentCondition()));
        carResponse.setGeneralParameters(mapToResponse(car.getGeneralParameters()));
        carResponse.setTechnicalParameters(mapToResponse(car.getTechnicalParameters()));
        return carResponse;
    }

    // ------------------------------- Technical Parameters -------------------------------------

    @Override
    public TechnicalParametersResponse getTechnicalParameters(Long carId) throws EntityNotFoundException {
        TechnicalParameters technicalParameters = technicalParametersRepository.findByCar(getCar(carId));
        return mapToResponse(technicalParameters);
    }

    @Override
    public List<TechnicalParametersResponse> getAllTechnicalParameters() {
        List<TechnicalParametersResponse> technicalParameters = new ArrayList<>();
        technicalParametersRepository.findAll().forEach(parameter ->
                technicalParameters.add(mapToResponse(parameter)));
        return technicalParameters;
    }

    @Override
    public TechnicalParametersResponse updateTechnicalParameters(TechnicalParametersRequest technicalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        TechnicalParameters technicalParameters = modelMapper.map(technicalParametersRequest, TechnicalParameters.class);
        checkTechnicalParameters(technicalParameters);
        technicalParameters.setId(car.getTechnicalParameters().getId());
        technicalParameters.setCar(car);
        technicalParametersRepository.save(technicalParameters);
        return mapToResponse(technicalParameters);
    }

    @Override
    public void checkTechnicalParameters(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException {
        adminService.checkBodyTypeExist(technicalParameters.getBodyType());
        adminService.checkColorExist(technicalParameters.getColor());
        adminService.checkGearboxExist(technicalParameters.getGearbox());
        adminService.checkFuelTypeExist(technicalParameters.getFuelType());
        adminService.checkInteriorMaterialExist(technicalParameters.getInteriorMaterial());
        adminService.checkTiresSeasonExist(technicalParameters.getTiresSeason());
        adminService.checkDriveUnitExist(technicalParameters.getDriveUnit());
    }

    private TechnicalParametersResponse mapToResponse(TechnicalParameters technicalParameters) {
        if (technicalParameters == null) {
            return null;
        }
        return modelMapper.map(technicalParameters, TechnicalParametersResponse.class);
    }

    // ------------------------------- General Parameters ---------------------------------------

    @Override
    public GeneralParametersResponse getGeneralParameters(Long carId) throws EntityNotFoundException {
        GeneralParameters generalParameters = generalParametersRepository.findByCar(getCar(carId));
        return mapToResponse(generalParameters);
    }

    @Override
    public List<GeneralParametersResponse> getAllGeneralParameters() {
        List<GeneralParametersResponse> generalParameters = new ArrayList<>();
        generalParametersRepository.findAll().forEach(parameter -> generalParameters.add(mapToResponse(parameter)));
        return generalParameters;
    }

    @Override
    public GeneralParametersResponse updateGeneralParameters(GeneralParametersRequest generalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        GeneralParameters generalParameters = modelMapper.map(generalParametersRequest, GeneralParameters.class);
        checkGeneralParameters(generalParameters);
        generalParameters.setId(car.getTechnicalParameters().getId());
        generalParameters.setCar(car);
        generalParametersRepository.save(generalParameters);
        return mapToResponse(generalParameters);
    }

    @Override
    public void checkGeneralParameters(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException {
        adminService.checkBrandAndModelExist(generalParameters.getBrand(), generalParameters.getModel());
    }

    private GeneralParametersResponse mapToResponse(GeneralParameters generalParameters) {
        if (generalParameters == null) {
            return null;
        }
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }

    // ------------------------------- Current Condition ----------------------------------------

    @Override
    public CurrentConditionResponse getCurrentCondition(Long carId) throws EntityNotFoundException {
        CurrentCondition currentCondition = currentConditionRepository.findByCar(getCar(carId));
        return mapToResponse(currentCondition);
    }

    @Override
    public List<CurrentConditionResponse> getAllCurrentCondition() {
        List<CurrentConditionResponse> currentConditions = new ArrayList<>();
        currentConditionRepository.findAll().forEach(currentCondition ->
                currentConditions.add(mapToResponse(currentCondition)));
        return currentConditions;
    }

    @Override
    public CurrentConditionResponse updateCurrentCondition(
            CurrentConditionRequest currentConditionRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = getCar(carId);
        checkCarOwner(car, ownerId);
        CurrentCondition currentCondition = modelMapper.map(currentConditionRequest, CurrentCondition.class);
        currentCondition.setId(car.getCurrentCondition().getId());
        currentCondition.setCar(car);
        currentConditionRepository.save(currentCondition);
        return mapToResponse(currentCondition);
    }

    private CurrentConditionResponse mapToResponse(CurrentCondition currentCondition) {
        if (currentCondition == null) {
            return null;
        }
        return modelMapper.map(currentCondition, CurrentConditionResponse.class);
    }
}
