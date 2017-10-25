package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.CurrentCondition;
import com.exposit.carsharing.domain.GeneralParameters;
import com.exposit.carsharing.dto.CurrentConditionResponse;
import com.exposit.carsharing.dto.GeneralParametersRequest;
import com.exposit.carsharing.dto.GeneralParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.GeneralParametersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GeneralParametersServiceImpl implements GeneralParametersService {
    private final GeneralParametersRepository generalParametersRepository;
    private final CarService carService;
    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public GeneralParametersServiceImpl(GeneralParametersRepository generalParametersRepository,
                                        CarService carService,
                                        AdminService adminService,
                                        ModelMapper modelMapper) {
        this.generalParametersRepository = generalParametersRepository;
        this.carService = carService;
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isExist(Long generalParametersId) {
        return generalParametersRepository.findOne(generalParametersId) != null;
    }

    @Override
    public GeneralParametersResponse get(Long id) throws EntityNotFoundException {
        GeneralParameters generalParameters = generalParametersRepository.findOne(id);
        if (generalParameters == null) {
            throw new EntityNotFoundException("General parameters", id);
        }
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }

    @Override
    public List<GeneralParametersResponse> getAll() {
        List<GeneralParametersResponse> generalParameters = new ArrayList<>();
        generalParametersRepository.findAll().forEach(parameter ->
                generalParameters.add(modelMapper.map(parameter, GeneralParametersResponse.class)));
        return generalParameters;
    }

    @Override
    public GeneralParametersResponse create(GeneralParametersRequest parameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = carService.getCar(carId);
        if (car.getGeneralParameters() != null) {
            throw new EntityAlreadyExistException();
        }
        GeneralParameters generalParameters = modelMapper.map(parameters, GeneralParameters.class);
        generalParameters.setCar(car);
        check(generalParameters);
        generalParametersRepository.save(generalParameters);
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }

    @Override
    public void delete(Long generalParametersId, Long carId) throws PrivilegeException, EntityNotFoundException {
        GeneralParameters generalParameters = modelMapper.map(get(generalParametersId), GeneralParameters.class);
        if (!generalParameters.getCar().getId().equals(carId)) {
            throw new PrivilegeException();
        }
        generalParametersRepository.delete(generalParametersId);
    }

    @Override
    public void check(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException {
        adminService.checkBrandAndModelExist(generalParameters.getBrand(), generalParameters.getModel());
    }

    @Override
    public GeneralParametersResponse mapToResponse(GeneralParameters generalParameters) {
        if (generalParameters == null) {
            return null;
        }
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }
}
