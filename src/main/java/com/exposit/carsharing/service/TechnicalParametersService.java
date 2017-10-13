package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.TechnicalParameters;

import java.util.List;

public interface TechnicalParametersService {
    TechnicalParameters getTechnicalParameters(Long id);

    List<TechnicalParameters> getAllTechnicalParameters();

    void createTechnicalParameters(TechnicalParameters technicalParameters, Long carId) throws EntityNotFoundException;
}
