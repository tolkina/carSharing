package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.Car;

import java.util.List;

public interface CarService {
    boolean isExist(Long id);

    Car get(Long id) throws EntityNotFoundException;

    List<Car> getAll();

    List<Car> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    void create(Car car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
