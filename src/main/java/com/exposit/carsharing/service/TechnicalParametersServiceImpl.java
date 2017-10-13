package com.exposit.carsharing.service;

import com.exposit.carsharing.model.TechnicalParameters;
import com.exposit.carsharing.repository.TechnicalParametersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicalParametersServiceImpl implements TechnicalParametersService {
    private final TechnicalParametersRepository technicalParametersRepository;
    private final CarService carService;

    public TechnicalParametersServiceImpl(TechnicalParametersRepository technicalParametersRepository, CarService carService) {
        this.technicalParametersRepository = technicalParametersRepository;
        this.carService = carService;
    }

    @Override
    public TechnicalParameters getTechnicalParameters(Long id) {
        return technicalParametersRepository.findOne(id);
    }

    @Override
    public List<TechnicalParameters> getAllTechnicalParameters() {
        return technicalParametersRepository.findAll();
    }

    @Override
    public void createTechnicalParameters(TechnicalParameters technicalParameters, Long carId) {
        technicalParameters.setCar(carService.getCar(carId));
        technicalParametersRepository.save(technicalParameters);
    }
}
