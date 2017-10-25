package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.GeneralParameters;
import com.exposit.carsharing.repository.GeneralParametersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GeneralParametersServiceImpl implements GeneralParametersService {
    private final GeneralParametersRepository generalParametersRepository;
    private final CarService carService;
    private final AdminService adminService;

    public GeneralParametersServiceImpl(GeneralParametersRepository generalParametersRepository,
                                        CarService carService,
                                        AdminService adminService) {
        this.generalParametersRepository = generalParametersRepository;
        this.carService = carService;
        this.adminService = adminService;
    }

    @Override
    public boolean isExist(Long generalParametersId) {
        return generalParametersRepository.findOne(generalParametersId) != null;
    }

    @Override
    public GeneralParameters get(Long id) throws EntityNotFoundException {
        GeneralParameters generalParameters = generalParametersRepository.findOne(id);
        if (generalParameters == null) {
            throw new EntityNotFoundException("General parameters", id);
        }
        return generalParameters;
    }

    @Override
    public List<GeneralParameters> getAll() {
        return generalParametersRepository.findAll();
    }

    @Override
    public void create(GeneralParameters generalParameters, Long carId) throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        if (generalParameters.getId() != null && isExist(generalParameters.getId())) {
            throw new EntityAlreadyExistException("General parameters", generalParameters.getId());
        }
        generalParameters.setCar(carService.get(carId));
        check(generalParameters);
        generalParametersRepository.save(generalParameters);
    }

    @Override
    public void delete(Long generalParametersId, Long carId) throws PrivilegeException, EntityNotFoundException {
        if (!get(generalParametersId).getCar().getId().equals(carId)) {
            throw new PrivilegeException();
        }
        generalParametersRepository.delete(generalParametersId);
    }

    @Override
    public void check(GeneralParameters generalParameters) throws EntityNotFoundException, PrivilegeException {
        adminService.checkBrandAndModelExist(generalParameters.getBrand(), generalParameters.getModel());
    }
}
