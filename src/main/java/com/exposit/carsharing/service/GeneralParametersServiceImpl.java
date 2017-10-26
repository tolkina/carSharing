package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.GeneralParameters;
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
    public GeneralParametersResponse get(Long carId) throws EntityNotFoundException {
        GeneralParameters generalParameters = generalParametersRepository.findByCar(carService.getCar(carId));
        return mapToResponse(generalParameters);
    }

    @Override
    public List<GeneralParametersResponse> getAll() {
        List<GeneralParametersResponse> generalParameters = new ArrayList<>();
        generalParametersRepository.findAll().forEach(parameter -> generalParameters.add(mapToResponse(parameter)));
        return generalParameters;
    }

    @Override
    public GeneralParametersResponse update(GeneralParametersRequest generalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = carService.getCar(carId);
        carService.checkCarOwner(car, ownerId);
        GeneralParameters generalParameters = modelMapper.map(generalParametersRequest, GeneralParameters.class);
        check(generalParameters);
        generalParameters.setId(car.getTechnicalParameters().getId());
        generalParameters.setCar(car);
        generalParametersRepository.save(generalParameters);
        return mapToResponse(generalParameters);
    }

    @Override
    public void check(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException {
        adminService.checkBrandAndModelExist(generalParameters.getBrand(), generalParameters.getModel());
    }

    private GeneralParametersResponse mapToResponse(GeneralParameters generalParameters) {
        if (generalParameters == null) {
            return null;
        }
        return modelMapper.map(generalParameters, GeneralParametersResponse.class);
    }
}
