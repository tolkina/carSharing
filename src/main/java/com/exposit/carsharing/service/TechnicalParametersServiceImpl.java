package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.TechnicalParameters;
import com.exposit.carsharing.dto.TechnicalParametersRequest;
import com.exposit.carsharing.dto.TechnicalParametersResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.TechnicalParametersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TechnicalParametersServiceImpl implements TechnicalParametersService {
    private final TechnicalParametersRepository technicalParametersRepository;
    private final CarService carService;
    private final AdminService adminService;
    private final ModelMapper modelMapper;

    public TechnicalParametersServiceImpl(TechnicalParametersRepository technicalParametersRepository,
                                          CarService carService, AdminService adminService, ModelMapper modelMapper) {
        this.technicalParametersRepository = technicalParametersRepository;
        this.carService = carService;
        this.adminService = adminService;
        this.modelMapper = modelMapper;
    }

    @Override
    public TechnicalParametersResponse get(Long carId) throws EntityNotFoundException {
        TechnicalParameters technicalParameters = technicalParametersRepository.findByCar(carService.getCar(carId));
        return mapToResponse(technicalParameters);
    }

    @Override
    public List<TechnicalParametersResponse> getAll() {
        List<TechnicalParametersResponse> technicalParameters = new ArrayList<>();
        technicalParametersRepository.findAll().forEach(parameter ->
                technicalParameters.add(mapToResponse(parameter)));
        return technicalParameters;
    }

    @Override
    public TechnicalParametersResponse update(TechnicalParametersRequest technicalParametersRequest, Long carId, Long ownerId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = carService.getCar(carId);
        carService.checkCarOwner(car, ownerId);
        TechnicalParameters technicalParameters = modelMapper.map(technicalParametersRequest, TechnicalParameters.class);
        check(technicalParameters);
        technicalParameters.setId(car.getTechnicalParameters().getId());
        technicalParameters.setCar(car);
        technicalParametersRepository.save(technicalParameters);
        return mapToResponse(technicalParameters);
    }

    @Override
    public void check(TechnicalParameters technicalParameters) throws EntityAlreadyExistException, EntityNotFoundException {
        adminService.checkBodyTypeExist(technicalParameters.getBodyType());
        adminService.checkColorExist(technicalParameters.getColor());
        adminService.checkGearboxExist(technicalParameters.getGearbox());
        adminService.checkFuelTypeExist(technicalParameters.getFuelType());
        adminService.checkInteriorMaterialExist(technicalParameters.getInteriorMaterial());
        adminService.checkTiresSeasonExist(technicalParameters.getTiresSeason());
        adminService.checkDriveUnitExist(technicalParameters.getDriveUnit());
    }

    private TechnicalParametersResponse mapToResponse(TechnicalParameters technicalParameters) {
        if (technicalParameters == null) {
            return null;
        }
        return modelMapper.map(technicalParameters, TechnicalParametersResponse.class);
    }
}
