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
import org.springframework.beans.factory.annotation.Autowired;
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

    public CarServiceImpl(CarRepository carRepository,
                          ProfileService profileService,
                          ModelMapper modelMapper,
                          CurrentConditionRepository currentConditionRepository,
                          GeneralParametersRepository generalParametersRepository,
                          TechnicalParametersRepository technicalParametersRepository) {
        this.carRepository = carRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
        this.currentConditionRepository = currentConditionRepository;
        this.generalParametersRepository = generalParametersRepository;
        this.technicalParametersRepository = technicalParametersRepository;
    }

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
    public List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAllByOwner(owner).forEach(car -> cars.add(mapToResponse(car)));
        return cars;
    }

    @Override
    public CarResponse create(CarRequest carRequest, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException {
        Car car = modelMapper.map(carRequest, Car.class);
        car.setOwner(profileService.get(ownerId));
        carRepository.save(car);
        CurrentCondition currentCondition = new CurrentCondition();
        GeneralParameters generalParameters = new GeneralParameters();
        TechnicalParameters technicalParameters = new TechnicalParameters();
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
        if (car.getCurrentCondition() != null) {
            carResponse.setCurrentCondition(modelMapper.map(car.getCurrentCondition(), CurrentConditionResponse.class));
        }
        if (car.getGeneralParameters() != null) {
            carResponse.setGeneralParameters(modelMapper.map(car.getGeneralParameters(), GeneralParametersResponse.class));
        }
        if (car.getTechnicalParameters() != null) {
            carResponse.setTechnicalParameters(modelMapper.map(car.getTechnicalParameters(), TechnicalParametersResponse.class));
        }
        return carResponse;
    }
}
