package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.CurrentCondition;
import com.exposit.carsharing.repository.CurrentConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentConditionServiceImpl implements CurrentConditionService {
    private final CurrentConditionRepository currentConditionRepository;
    private final CarService carService;

    public CurrentConditionServiceImpl(CurrentConditionRepository currentConditionRepository, CarService carService) {
        this.currentConditionRepository = currentConditionRepository;
        this.carService = carService;
    }

    @Override
    public boolean isExist(Long currentConditionId) {
        return currentConditionRepository.findOne(currentConditionId) != null;
    }

    @Override
    public CurrentCondition get(Long id) throws EntityNotFoundException {
        CurrentCondition currentCondition = currentConditionRepository.findOne(id);
        if (currentCondition == null) {
            throw new EntityNotFoundException("Current condition", id);
        }
        return currentCondition;
    }

    @Override
    public List<CurrentCondition> getAll() {
        return currentConditionRepository.findAll();
    }

    @Override
    public void create(CurrentCondition currentCondition, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (currentCondition.getId() != null && isExist(currentCondition.getId())) {
            throw new EntityAlreadyExistException("Current condition", currentCondition.getId());
        }
        currentCondition.setCar(carService.get(carId));
        currentConditionRepository.save(currentCondition);
    }

    @Override
    public void delete(Long currentConditionId, Long carId) throws PrivilegeException, EntityNotFoundException {
        if (!get(currentConditionId).getCar().getId().equals(carId)) {
            throw new PrivilegeException();
        }
        currentConditionRepository.delete(currentConditionId);
    }
}
