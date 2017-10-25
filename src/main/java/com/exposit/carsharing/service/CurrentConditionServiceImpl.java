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
    public boolean isExist(Long currentConditionId) {
        return currentConditionRepository.findOne(currentConditionId) != null;
    }

    @Override
    public CurrentConditionResponse get(Long id) throws EntityNotFoundException {
        CurrentCondition currentCondition = currentConditionRepository.findOne(id);
        if (currentCondition == null) {
            throw new EntityNotFoundException("Current condition", id);
        }
        return modelMapper.map(currentCondition, CurrentConditionResponse.class);
    }

    @Override
    public List<CurrentConditionResponse> getAll() {
        List<CurrentConditionResponse> currentConditions = new ArrayList<>();
        currentConditionRepository.findAll().forEach(currentCondition ->
                currentConditions.add(modelMapper.map(currentCondition, CurrentConditionResponse.class)));
        return currentConditions;
    }

    @Override
    public CurrentConditionResponse create(CurrentConditionRequest currentCondition, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        Car car = carService.getCar(carId);
        if (car.getCurrentCondition() != null) {
            throw new EntityAlreadyExistException();
        }
        CurrentCondition condition = modelMapper.map(currentCondition, CurrentCondition.class);
        condition.setCar(car);
        currentConditionRepository.save(condition);
        return modelMapper.map(condition, CurrentConditionResponse.class);
    }

    @Override
    public void delete(Long currentConditionId, Long carId) throws PrivilegeException, EntityNotFoundException {
        CurrentCondition currentCondition = modelMapper.map(get(currentConditionId), CurrentCondition.class);
        if (!currentCondition.getCar().getId().equals(carId)) {
            throw new PrivilegeException();
        }
        currentConditionRepository.delete(currentConditionId);
    }

    @Override
    public CurrentConditionResponse mapToResponse(CurrentCondition currentCondition) {
        if (currentCondition == null) {
            return null;
        }
        return modelMapper.map(currentCondition, CurrentConditionResponse.class);
    }
}
