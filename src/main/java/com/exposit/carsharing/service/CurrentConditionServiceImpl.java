package com.exposit.carsharing.service;

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
    public CurrentCondition getCurrentCondition(Long id) {
        return currentConditionRepository.findOne(id);
    }

    @Override
    public List<CurrentCondition> getAllCurrentConditions() {
        return currentConditionRepository.findAll();
    }

    @Override
    public void createCurrentCondition(CurrentCondition currentCondition, Long carId) {
        currentCondition.setCar(carService.getCar(carId));
        currentConditionRepository.save(currentCondition);
    }

  /*  @PostConstruct
    @Transactional
    public void populate() {
        TechnicalParameters technicalParameters = new TechnicalParameters();
        technicalParameters.setGearbox("Mechanical");
        technicalParameters.setBodyType("Sedan");
        technicalParameters.setSeatNumber(4);
        technicalParameters.setDoorNumber(5);
        technicalParameters.setFuelType("AI-95");
        technicalParameters.setFuelConsumption(1.6);
        technicalParameters.setDriveUnit("Front-wheel");
        technicalParameters.setTiresSeason("All seasons");
        technicalParameters.setInteriorMaterial("Leather");
        technicalParameters.setColor("Black Metallic");
        technicalParameters.setVin(12658963);
        technicalParameters.setGovNumber("1111 AA-1");
        technicalParameters.setStsFormNumber(90876453);
        technicalParameters.setStsImageLink("Super Photo URL");
        technicalParameters.setPtsImageLink("PTS Super Photo URL");

        currentConditionRepository.saveAndFlush(technicalParameters);
    }

    @Transactional
    public List<TechnicalParameters> getAll() {
        return currentConditionRepository.findAll();
    }

    @Transactional
    public void delete(long id) {
        currentConditionRepository.delete(id);
    }*/
}
