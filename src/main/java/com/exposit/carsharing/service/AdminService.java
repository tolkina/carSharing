package com.exposit.carsharing.service;

import com.exposit.carsharing.modelAdmin.*;

import java.util.List;

public interface AdminService {

    // ---------------------- Body type --------------------

    void createBodyType(String name);

    void deleteBodyType(Long id);

    void updateBodyType(Long id, String name);

    List<BodyType> getAllBodyTypes();

    BodyType getBodyType(Long id);

    // ---------------------- Brand --------------------

    void createBrand(String name);

    void deleteBrand(Long id);

    void updateBrand(Long id, String name);

    List<Brand> getAllBrands();

    Brand getBrand(Long id);

    // ---------------------- Color --------------------

    void createColor(String name);

    void deleteColor(Long id);

    void updateColor(Long id, String name);

    List<Color> getAllColors();

    Color getColor(Long id);

    // ---------------------- Drive unit --------------------

    void createDriveUnit(String name);

    void deleteDriveUnit(Long id);

    void updateDriveUnit(Long id, String name);

    List<DriveUnit> getAllDriveUnits();

    DriveUnit getDriveUnit(Long id);

    // ---------------------- Fuel type --------------------

    void createFuelType(String name);

    void deleteFuelType(Long id);

    void updateFuelType(Long id, String name);

    List<FuelType> getAllFuelTypes();

    FuelType getFuelType(Long id);

    // ---------------------- Gearbox --------------------

    void createGearbox(String name);

    void deleteGearbox(Long id);

    void updateGearbox(Long id, String name);

    List<Gearbox> getAllGearboxes();

    Gearbox getGearbox(Long id);

    // ---------------------- Interior material --------------------

    void createInteriorMaterial(String name);

    void deleteInteriorMaterial(Long id);

    void updateInteriorMaterial(Long id, String name);

    List<InteriorMaterial> getAllInteriorMaterials();

    InteriorMaterial getInteriorMaterial(Long id);

    // ---------------------- Model --------------------

    void createModel(String name);

    void deleteModel(Long id);

    void updateModel(Long id, String name);

    List<Model> getAllModels();

    List<Model> getAllModelsByBrand(Long brand_id);

    Model getModel(Long id);

    // ---------------------- Tires season --------------------

    void createTiresSeason(String name);

    void deleteTiresSeason(Long id);

    void updateTiresSeason(Long id, String name);

    List<TiresSeason> getAllTiresSeasons();

    TiresSeason getTiresSeason(Long id);
}
