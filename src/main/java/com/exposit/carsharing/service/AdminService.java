package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.modelAdmin.*;

import java.util.List;

public interface AdminService {

    // ---------------------- Body type --------------------

    boolean isBodyTypeExist(Long id);

    void checkBodyTypeNameUsed(String name) throws EntityAlreadyExistException;

    BodyType createBodyType(String name) throws EntityAlreadyExistException;

    void deleteBodyType(Long id) throws EntityNotFoundException;

    BodyType updateBodyType(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<BodyType> getAllBodyTypes();

    BodyType getBodyType(Long id) throws EntityNotFoundException;

    // ---------------------- Brand --------------------
    boolean isBrandExist(Long id);

    void checkBrandNameUsed(String name) throws EntityAlreadyExistException;

    Brand createBrand(String name) throws EntityAlreadyExistException;

    void deleteBrand(Long id) throws EntityNotFoundException;

    Brand updateBrand(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<Brand> getAllBrands();

    Brand getBrand(Long id) throws EntityNotFoundException;

    // ---------------------- Color --------------------
    boolean isColorExist(Long id);

    void checkColorNameUsed(String name) throws EntityAlreadyExistException;

    Color createColor(String name) throws EntityAlreadyExistException;

    void deleteColor(Long id) throws EntityNotFoundException;

    Color updateColor(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<Color> getAllColors();

    Color getColor(Long id) throws EntityNotFoundException;

    // ---------------------- Drive unit --------------------
    boolean isDriveUnitExist(Long id);

    void checkDriveUnitNameUsed(String name) throws EntityAlreadyExistException;

    DriveUnit createDriveUnit(String name) throws EntityAlreadyExistException;

    void deleteDriveUnit(Long id) throws EntityNotFoundException;

    DriveUnit updateDriveUnit(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<DriveUnit> getAllDriveUnits();

    DriveUnit getDriveUnit(Long id) throws EntityNotFoundException;

    // ---------------------- Fuel type --------------------
    boolean isFuelTypeExist(Long id);

    void checkFuelTypeNameUsed(String name) throws EntityAlreadyExistException;

    FuelType createFuelType(String name) throws EntityAlreadyExistException;

    void deleteFuelType(Long id) throws EntityNotFoundException;

    FuelType updateFuelType(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<FuelType> getAllFuelTypes();

    FuelType getFuelType(Long id) throws EntityNotFoundException;

    // ---------------------- Gearbox --------------------
    boolean isGearboxExist(Long id);

    void checkGearboxNameUsed(String name) throws EntityAlreadyExistException;

    Gearbox createGearbox(String name) throws EntityAlreadyExistException;

    void deleteGearbox(Long id) throws EntityNotFoundException;

    Gearbox updateGearbox(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<Gearbox> getAllGearboxes();

    Gearbox getGearbox(Long id) throws EntityNotFoundException;

    // ---------------------- Interior material --------------------
    boolean isInteriorMaterialExist(Long id);

    void checkInteriorMaterialNameUsed(String name) throws EntityAlreadyExistException;

    InteriorMaterial createInteriorMaterial(String name) throws EntityAlreadyExistException;

    void deleteInteriorMaterial(Long id) throws EntityNotFoundException;

    InteriorMaterial updateInteriorMaterial(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<InteriorMaterial> getAllInteriorMaterials();

    InteriorMaterial getInteriorMaterial(Long id) throws EntityNotFoundException;

    // ---------------------- Model --------------------
    boolean isModelExist(Long id);

    void checkModelNameUsed(String name) throws EntityAlreadyExistException;

    Model createModel(Long brandId, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    void deleteModel(Long id) throws EntityNotFoundException;

    Model updateModel(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<Model> getAllModels();

    List<Model> getAllModelsByBrand(Long brand_id) throws EntityNotFoundException;

    Model getModel(Long id) throws EntityNotFoundException;

    // ---------------------- Tires season --------------------
    boolean isTiresSeasonExist(Long id);

    void checkTiresSeasonNameUsed(String name) throws EntityAlreadyExistException;

    TiresSeason createTiresSeason(String name) throws EntityAlreadyExistException;

    void deleteTiresSeason(Long id) throws EntityNotFoundException;

    TiresSeason updateTiresSeason(Long id, String name) throws EntityAlreadyExistException, EntityNotFoundException;

    List<TiresSeason> getAllTiresSeasons();

    TiresSeason getTiresSeason(Long id) throws EntityNotFoundException;
}
