package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.CarRequest;
import com.exposit.carsharing.dto.CarResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.Car;

import java.util.List;

public interface CarService {
    boolean isExist(Long id);

    CarResponse get(Long id) throws EntityNotFoundException;

    List<CarResponse> getAll();

    List<CarResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    CarResponse create(CarRequest car, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long carId, Long ownerId) throws PrivilegeException, EntityNotFoundException;

    Car getCar(Long id) throws EntityNotFoundException;
}
