package com.exposit.carsharing.service;

import com.exposit.carsharing.model.Car;
import com.exposit.carsharing.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */

@Service
@Repository
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @PostConstruct
    @Transactional
    public void populate(){
        Car cars = new Car();

        carRepository.saveAndFlush(cars);
    }


    @Transactional
    public List<Car> getAll(){
        return carRepository.findAll();
    }
}
