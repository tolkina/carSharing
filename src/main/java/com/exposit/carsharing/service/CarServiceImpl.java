package com.exposit.carsharing.service;

import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findOne(id);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void createCar(Car car) {
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
