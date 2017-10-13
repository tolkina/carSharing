package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.GeneralParameters;
import com.exposit.carsharing.repository.GeneralParametersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralParametersServiceImpl implements GeneralParametersService {
    private final GeneralParametersRepository generalParametersRepository;
    private final CarService carService;

    public GeneralParametersServiceImpl(GeneralParametersRepository generalParametersRepository, CarService carService) {
        this.generalParametersRepository = generalParametersRepository;
        this.carService = carService;
    }

    @Override
    public GeneralParameters getGeneralParameters(Long id) {
        return generalParametersRepository.findOne(id);
    }

    @Override
    public List<GeneralParameters> getAllGeneralParameters() {
        return generalParametersRepository.findAll();
    }

    @Override
    public void createGeneralParameters(GeneralParameters generalParameters, Long carId) throws EntityNotFoundException {
        generalParameters.setCar(carService.getCar(carId));
        generalParametersRepository.save(generalParameters);
    }


    /*@PostConstruct
    @Transactional
    public void populate(){
        GeneralParameters generalParameters = new GeneralParameters();
        generalParameters.setBrand("Audi");
        generalParameters.setModel("TT");
        generalParameters.setYearOfIssue(2012);
        generalParametersRepository.saveAndFlush(generalParameters);
    }

    @Transactional
    public List<GeneralParameters> getAll() {
        return generalParametersRepository.findAll();
    }


    @Transactional
    public void delete(long id){
        generalParametersRepository.delete(id);
    }*/
}
