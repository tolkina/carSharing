package com.exposit.carsharing.service;

import com.exposit.carsharing.model.Car;

import java.util.List;

public interface CarService {
    Car getCar(Long id);

    List<Car> getAllCars();

    void createCar(Car car);
}
