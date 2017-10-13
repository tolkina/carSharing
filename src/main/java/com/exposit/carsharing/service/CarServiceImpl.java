package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public Car getCar(Long id) throws EntityNotFoundException {
        Car car =  carRepository.findOne(id);
        if (car == null){
            throw new EntityNotFoundException(String.format("Car with id %d not found", id));
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void createCar(Car car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (car.getId() != null && isExist(car.getId())) {
            throw new EntityAlreadyExistException(String.format("Car id %d already used", car.getId()));
        }
        car.setOwner(profileService.getProfile(ownerId));
        carRepository.save(car);
    }

/*   @PostConstruct
    @Transactional
    public void populate(){
        Car cars = new Car();

        carRepository.saveAndFlush(cars);
    }

    @Transactional
    public List<Car> getAll(){
        return carRepository.findAll();
    }*/
}
