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
    public boolean isExist(Long technicalParametersId) {
        return technicalParametersRepository.findOne(technicalParametersId) != null;
    }

    @Override
    public TechnicalParametersResponse get(Long id) throws EntityNotFoundException {
        return modelMapper.map(load(id), TechnicalParametersResponse.class);
    }

    @Override
    public TechnicalParameters load(Long id) throws EntityNotFoundException {
        TechnicalParameters technicalParameters = technicalParametersRepository.findOne(id);
        if (technicalParameters == null) {
            throw new EntityNotFoundException("Technical parameters", id);
        }
        return technicalParameters;
    }

    @Override
    public List<TechnicalParametersResponse> getAll() {
        List<TechnicalParametersResponse> technicalParameters = new ArrayList<>();
        technicalParametersRepository.findAll().forEach(parameter ->
                technicalParameters.add(modelMapper.map(parameter, TechnicalParametersResponse.class)));
        return technicalParameters;
    }

    @Override
    public TechnicalParametersResponse create(TechnicalParametersRequest technicalParametersRequest, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        Car car = carService.getCar(carId);
        if (car.getTechnicalParameters() != null) {
            throw new EntityAlreadyExistException();
        }
        TechnicalParameters technicalParameters = modelMapper.map(technicalParametersRequest, TechnicalParameters.class);
        check(technicalParameters);
        technicalParameters.setCar(car);
        technicalParametersRepository.save(technicalParameters);
        return modelMapper.map(technicalParameters, TechnicalParametersResponse.class);
    }

    @Override
    public void delete(Long technicalParameterId, Long carId) throws PrivilegeException, EntityNotFoundException {
        TechnicalParameters technicalParameters = load(carId);
        if (!technicalParameters.getCar().getId().equals(carId)) {
            throw new PrivilegeException();
        }
        technicalParametersRepository.delete(technicalParameterId);
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

    @Override
    public TechnicalParametersResponse mapParametersToResponse(TechnicalParameters technicalParameters) {
        if (technicalParameters == null) {
            return null;
        }
        return modelMapper.map(technicalParameters, TechnicalParametersResponse.class);
    }
}
