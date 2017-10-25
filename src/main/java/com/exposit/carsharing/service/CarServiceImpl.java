package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.CarRepository;
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

    public CarServiceImpl(CarRepository carRepository, ProfileService profileService, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isExist(Long id) {
        return carRepository.findOne(id) != null;
    }

    @Override
    public CarResponse get(Long id) throws EntityNotFoundException {
        return mapCarToResponse(getCar(id));
    }

    @Override
    public List<CarResponse> getAll() {
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAll().forEach(car -> cars.add(mapCarToResponse(car)));
        return cars;
    }

    @Override
    public List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        List<CarResponse> cars = new ArrayList<>();
        carRepository.findAllByOwner(owner).forEach(car -> cars.add(mapCarToResponse(car)));
        return cars;
    }

    @Override
    public CarResponse create(CarRequest carRequest, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        Car car = modelMapper.map(carRequest, Car.class);
        car.setOwner(profileService.get(ownerId));
        carRepository.save(car);
        return modelMapper.map(car, CarResponse.class);
    }

    @Override
    public void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(carId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        carRepository.delete(carId);
    }

    @Override
    public Car getCar(Long id) throws EntityNotFoundException {
        Car car = carRepository.findOne(id);
        if (car == null) {
            throw new EntityNotFoundException("Car", id);
        }
        return car;
    }

    private CarResponse mapCarToResponse(Car car) {
        if (car == null) {
            return null;
        }
        CarResponse carResponse = new CarResponse();
        carResponse.setId(car.getId());
        carResponse.setOwner(modelMapper.map(car.getOwner(), ProfileResponse.class));
        System.out.println(car.getCurrentCondition());
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
