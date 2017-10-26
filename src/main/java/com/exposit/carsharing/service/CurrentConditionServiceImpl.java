package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.CurrentCondition;
import com.exposit.carsharing.dto.CurrentConditionRequest;
import com.exposit.carsharing.dto.CurrentConditionResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.CurrentConditionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CurrentConditionServiceImpl implements CurrentConditionService {
    private final CurrentConditionRepository currentConditionRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;

    public CurrentConditionServiceImpl(CurrentConditionRepository currentConditionRepository, CarService carService, ModelMapper modelMapper) {
        this.currentConditionRepository = currentConditionRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CurrentConditionResponse get(Long carId) throws EntityNotFoundException {
        CurrentCondition currentCondition = currentConditionRepository.findByCar(carService.getCar(carId));
        return mapToResponse(currentCondition);
    }

    @Override
    public List<CurrentConditionResponse> getAll() {
        List<CurrentConditionResponse> currentConditions = new ArrayList<>();
        currentConditionRepository.findAll().forEach(currentCondition ->
                currentConditions.add(mapToResponse(currentCondition)));
        return currentConditions;
    }

    @Override
    public CurrentConditionResponse update(
            CurrentConditionRequest currentConditionRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = carService.getCar(carId);
        carService.checkCarOwner(car, ownerId);
        CurrentCondition currentCondition = modelMapper.map(currentConditionRequest, CurrentCondition.class);
        currentCondition.setId(car.getCurrentCondition().getId());
        currentCondition.setCar(car);
        currentConditionRepository.save(currentCondition);
        return mapToResponse(currentCondition);
    }

    private CurrentConditionResponse mapToResponse(CurrentCondition currentCondition) {
        if (currentCondition == null) {
            return null;
        }
        return modelMapper.map(currentCondition, CurrentConditionResponse.class);
    }
}
