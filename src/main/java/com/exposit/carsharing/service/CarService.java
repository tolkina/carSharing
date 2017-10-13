package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Car;

import java.util.List;

public interface CarService {
    boolean isExist(Long id);

    Car getCar(Long id) throws EntityNotFoundException;

    List<Car> getAllCars();

    void createCar(Car car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;
}
