package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.GeneralParameters;

import java.util.List;

public interface GeneralParametersService {
    GeneralParameters getGeneralParameters(Long id);

    List<GeneralParameters> getAllGeneralParameters();

    void createGeneralParameters(GeneralParameters generalParameters, Long carId) throws EntityNotFoundException;
}
