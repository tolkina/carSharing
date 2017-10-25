package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.model.Profile;
import com.exposit.carsharing.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ProfileService profileService;

    public CarServiceImpl(CarRepository carRepository, ProfileService profileService) {
        this.carRepository = carRepository;
        this.profileService = profileService;
    }

    @Override
    public boolean isExist(Long id) {
        return carRepository.findOne(id) != null;
    }

    @Override
    public Car get(Long id) throws EntityNotFoundException {
        Car car = carRepository.findOne(id);
        if (car == null) {
            throw new EntityNotFoundException("Car", id);
        }
        return car;
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        return carRepository.findAllByOwner(owner);
    }

    @Override
    public void create(Car car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (car.getId() != null && isExist(car.getId())) {
            throw new EntityAlreadyExistException("Car", car.getId());
        }
        car.setOwner(profileService.get(ownerId));
        carRepository.save(car);
    }

    @Override
    public void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(carId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        carRepository.delete(carId);
    }
}
