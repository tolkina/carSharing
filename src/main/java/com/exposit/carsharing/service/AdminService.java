package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.*;
import com.exposit.carsharing.dto.BrandResponse;
import com.exposit.carsharing.dto.CarParameterRequest;
import com.exposit.carsharing.dto.CarParameterResponse;
import com.exposit.carsharing.dto.ModelResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface AdminService {

    void checkAdmin(Long principalId) throws EntityNotFoundException, PrivilegeException;

    // ---------------------- Body type --------------------

    void checkBodyTypeExist(String name) throws EntityNotFoundException;

    void checkBodyTypeNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createBodyType(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteBodyType(Long id) throws EntityNotFoundException;

    CarParameterResponse updateBodyType(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllBodyTypes();

    CarParameterResponse getBodyType(Long id) throws EntityNotFoundException;

    // ---------------------- Brand --------------------
    void checkBrandExist(String name) throws EntityNotFoundException;

    void checkBrandAndModelExist(String brand, String model) throws EntityNotFoundException, PrivilegeException;

    void checkBrandNameUsed(String name) throws EntityAlreadyExistException;

    BrandResponse createBrand(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteBrand(Long id) throws EntityNotFoundException;

    BrandResponse updateBrand(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<BrandResponse> getAllBrands();

    BrandResponse getBrand(Long id) throws EntityNotFoundException;

    // ---------------------- Color --------------------
    void checkColorExist(String name) throws EntityNotFoundException;

    void checkColorNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createColor(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteColor(Long id) throws EntityNotFoundException;

    CarParameterResponse updateColor(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllColors();

    CarParameterResponse getColor(Long id) throws EntityNotFoundException;

    // ---------------------- Drive unit --------------------
    void checkDriveUnitExist(String name) throws EntityNotFoundException;

    void checkDriveUnitNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createDriveUnit(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteDriveUnit(Long id) throws EntityNotFoundException;

    CarParameterResponse updateDriveUnit(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllDriveUnits();

    CarParameterResponse getDriveUnit(Long id) throws EntityNotFoundException;

    // ---------------------- Fuel type --------------------
    void checkFuelTypeExist(String name) throws EntityNotFoundException;

    void checkFuelTypeNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createFuelType(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteFuelType(Long id) throws EntityNotFoundException;

    CarParameterResponse updateFuelType(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllFuelTypes();

    CarParameterResponse getFuelType(Long id) throws EntityNotFoundException;

    // ---------------------- Gearbox --------------------
    void checkGearboxExist(String name) throws EntityNotFoundException;

    void checkGearboxNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createGearbox(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteGearbox(Long id) throws EntityNotFoundException;

    CarParameterResponse updateGearbox(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllGearboxes();

    CarParameterResponse getGearbox(Long id) throws EntityNotFoundException;

    // ---------------------- Interior material --------------------
    void checkInteriorMaterialExist(String name) throws EntityNotFoundException;

    void checkInteriorMaterialNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createInteriorMaterial(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteInteriorMaterial(Long id) throws EntityNotFoundException;

    CarParameterResponse updateInteriorMaterial(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllInteriorMaterials();

    CarParameterResponse getInteriorMaterial(Long id) throws EntityNotFoundException;

    // ---------------------- Model --------------------
    void checkModelExist(String name) throws EntityNotFoundException;

    void checkModelNameUsed(String name) throws EntityAlreadyExistException;

    ModelResponse createModel(Long brandId, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    void deleteModel(Long id) throws EntityNotFoundException;

    ModelResponse updateModel(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<ModelResponse> getAllModels();

    List<CarParameterResponse> getAllModelsByBrand(Long brand_id) throws EntityNotFoundException;

    ModelResponse getModel(Long id) throws EntityNotFoundException;

    // ---------------------- Tires season --------------------
    void checkTiresSeasonExist(String name) throws EntityNotFoundException;

    void checkTiresSeasonNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createTiresSeason(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteTiresSeason(Long id) throws EntityNotFoundException;

    CarParameterResponse updateTiresSeason(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException;

    List<CarParameterResponse> getAllTiresSeasons();

    CarParameterResponse getTiresSeason(Long id) throws EntityNotFoundException;
}
