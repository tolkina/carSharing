package com.exposit.carsharing.service;

import com.exposit.carsharing.model.CurrentCondition;
import com.exposit.carsharing.repository.CurrentConditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Sergei on 10/12/2017.
 */

@Service
@Repository
public class CurrentConditionService {

    @Autowired
    private CurrentConditionRepository currentConditionRepository;

    @PostConstruct
    @Transactional
    public void populate() {
        CurrentCondition currentCondition = new CurrentCondition();
        currentCondition.setGearbox("Mechanical");
        currentCondition.setBodyType("Sedan");
        currentCondition.setSeatNumber(4);
        currentCondition.setDoorNumber(5);
        currentCondition.setFuelType("AI-95");
        currentCondition.setFuelConsumption(1.6);
        currentCondition.setDriveUnit("Front-wheel");
        currentCondition.setTiresSeason("All seasons");
        currentCondition.setInteriorMaterial("Leather");
        currentCondition.setColor("Black Metallic");
        currentCondition.setVin(12658963);
        currentCondition.setGovNumber("1111 AA-1");
        currentCondition.setStsFormNumber(90876453);
        currentCondition.setStsImageLink("Super Photo URL");
        currentCondition.setPtsImageLink("PTS Super Photo URL");

        currentConditionRepository.saveAndFlush(currentCondition);
    }

    @Transactional
    public List<CurrentCondition> getAll() {
        return currentConditionRepository.findAll();
    }



    @Transactional
    public void delete(long id) {
        currentConditionRepository.delete(id);
    }


}
