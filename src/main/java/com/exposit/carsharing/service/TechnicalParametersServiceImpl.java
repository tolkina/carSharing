package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.TechnicalParameters;
import com.exposit.carsharing.repository.TechnicalParametersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TechnicalParametersServiceImpl implements TechnicalParametersService {
    private final TechnicalParametersRepository technicalParametersRepository;
    private final CarService carService;
    private final AdminService adminService;

    public TechnicalParametersServiceImpl(TechnicalParametersRepository technicalParametersRepository,
                                          CarService carService, AdminService adminService) {
        this.technicalParametersRepository = technicalParametersRepository;
        this.carService = carService;
        this.adminService = adminService;
    }

    @Override
    public boolean isExist(Long technicalParametersId) {
        return technicalParametersRepository.findOne(technicalParametersId) != null;
    }

    @Override
    public TechnicalParameters get(Long id) throws EntityNotFoundException {
        TechnicalParameters technicalParameters = technicalParametersRepository.findOne(id);
        if (technicalParameters == null) {
            throw new EntityNotFoundException("Technical parameters", id);
        }
        return technicalParameters;
    }

    @Override
    public List<TechnicalParameters> getAll() {
        return technicalParametersRepository.findAll();
    }

    @Override
    public void create(TechnicalParameters technicalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (technicalParameters.getId() != null && isExist(technicalParameters.getId())) {
            throw new EntityAlreadyExistException("Technical parameters", technicalParameters.getId());
        }
        check(technicalParameters);
        technicalParameters.setCar(carService.get(carId));
        technicalParametersRepository.save(technicalParameters);
    }

    @Override
    public void delete(Long technicalParameterId, Long carId) throws PrivilegeException, EntityNotFoundException {
        if (!get(technicalParameterId).getCar().getId().equals(carId)) {
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
}
