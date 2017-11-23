package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import org.springframework.data.domain.Page;

public interface AdminService {

    void checkAdmin(Long principalId) throws EntityNotFoundException, PrivilegeException;

    // ---------------------- Body type --------------------

    void checkBodyTypeExist(String name) throws EntityNotFoundException;

    void checkBodyTypeNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createBodyType(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteBodyType(Long id) throws EntityNotFoundException;

    CarParameterResponse updateBodyType(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllBodyTypes(Integer page, Integer size);

    CarParameterResponse getBodyType(Long id) throws EntityNotFoundException;

    // ---------------------- Brand --------------------
    void checkBrandExist(String name) throws EntityNotFoundException;

    void checkBrandAndModelExist(String brand, String model)
            throws EntityNotFoundException, PrivilegeException;

    void checkBrandNameUsed(String name) throws EntityAlreadyExistException;

    BrandResponse createBrand(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException;

    void deleteBrand(Long id) throws EntityNotFoundException;

    BrandResponse updateBrand(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<BrandResponse> getAllBrands(PageParametersRequest pageParametersRequest);

    BrandResponse getBrandResponse(Long id) throws EntityNotFoundException;

    // ---------------------- Color --------------------
    void checkColorExist(String name) throws EntityNotFoundException;

    void checkColorNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createColor(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteColor(Long id) throws EntityNotFoundException;

    CarParameterResponse updateColor(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllColors(Integer page, Integer size);

    CarParameterResponse getColor(Long id) throws EntityNotFoundException;

    // ---------------------- Drive unit --------------------
    void checkDriveUnitExist(String name) throws EntityNotFoundException;

    void checkDriveUnitNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createDriveUnit(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteDriveUnit(Long id) throws EntityNotFoundException;

    CarParameterResponse updateDriveUnit(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllDriveUnits(Integer page, Integer size);

    CarParameterResponse getDriveUnit(Long id) throws EntityNotFoundException;

    // ---------------------- Fuel type --------------------
    void checkFuelTypeExist(String name) throws EntityNotFoundException;

    void checkFuelTypeNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createFuelType(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteFuelType(Long id) throws EntityNotFoundException;

    CarParameterResponse updateFuelType(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllFuelTypes(Integer page, Integer size);

    CarParameterResponse getFuelType(Long id) throws EntityNotFoundException;

    // ---------------------- Gearbox --------------------
    void checkGearboxExist(String name) throws EntityNotFoundException;

    void checkGearboxNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createGearbox(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteGearbox(Long id) throws EntityNotFoundException;

    CarParameterResponse updateGearbox(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllGearboxes(Integer page, Integer size);

    CarParameterResponse getGearbox(Long id) throws EntityNotFoundException;

    // ---------------------- Interior material --------------------
    void checkInteriorMaterialExist(String name) throws EntityNotFoundException;

    void checkInteriorMaterialNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createInteriorMaterial(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteInteriorMaterial(Long id) throws EntityNotFoundException;

    CarParameterResponse updateInteriorMaterial(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllInteriorMaterials(Integer page, Integer size);

    CarParameterResponse getInteriorMaterial(Long id) throws EntityNotFoundException;

    // ---------------------- Model --------------------
    void checkModelExist(String name) throws EntityNotFoundException;

    void checkModelNameUsed(String name) throws EntityAlreadyExistException;

    ModelResponse createModel(Long brandId, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    void deleteModel(Long id) throws EntityNotFoundException;

    ModelResponse updateModel(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<ModelResponse> getAllModels(Integer page, Integer size);

    Page<CarParameterResponse> getAllModelsByBrand(Long brandId, Integer page, Integer size)
            throws EntityNotFoundException;

    ModelResponse getModelResponse(Long id) throws EntityNotFoundException;

    // ---------------------- Tires season --------------------

    void checkTiresSeasonExist(String name) throws EntityNotFoundException;

    void checkTiresSeasonNameUsed(String name) throws EntityAlreadyExistException;

    CarParameterResponse createTiresSeason(CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException;

    void deleteTiresSeason(Long id) throws EntityNotFoundException;

    CarParameterResponse updateTiresSeason(Long id, CarParameterRequest carParameterRequest)
            throws EntityAlreadyExistException, EntityNotFoundException;

    Page<CarParameterResponse> getAllTiresSeasons(Integer page, Integer size);

    CarParameterResponse getTiresSeason(Long id) throws EntityNotFoundException;

    // ------------------------- Confirm Profile --------------------

    Page<ConfirmProfileResponse> getProfilesToConfirm(Integer page, Integer size);

    ConfirmationResponse setConfirmProfileYes(Long profileId)
            throws EntityNotFoundException, PrivilegeException, ConfirmProfileException;

    ConfirmationResponse setConfirmProfileNo(Long profileId)
            throws EntityNotFoundException, PrivilegeException, ConfirmProfileException;

    Page<ConfirmationResponse> getConfirmations(Integer page, Integer size);
}
