package com.exposit.carsharing.service;

import com.exposit.carsharing.modelAdmin.*;

import java.util.List;

public interface AdminService {

    // ---------------------- Body type --------------------

    BodyType createBodyType(String name);

    void deleteBodyType(Long id);

    BodyType updateBodyType(Long id, String name);

    List<BodyType> getAllBodyTypes();

    BodyType getBodyType(Long id);

    // ---------------------- Brand --------------------

    Brand createBrand(String name);

    void deleteBrand(Long id);

    Brand updateBrand(Long id, String name);

    List<Brand> getAllBrands();

    Brand getBrand(Long id);

    // ---------------------- Color --------------------

    Color createColor(String name);

    void deleteColor(Long id);

    Color updateColor(Long id, String name);

    List<Color> getAllColors();

    Color getColor(Long id);

    // ---------------------- Drive unit --------------------

    DriveUnit createDriveUnit(String name);

    void deleteDriveUnit(Long id);

    DriveUnit updateDriveUnit(Long id, String name);

    List<DriveUnit> getAllDriveUnits();

    DriveUnit getDriveUnit(Long id);

    // ---------------------- Fuel type --------------------

    FuelType createFuelType(String name);

    void deleteFuelType(Long id);

    FuelType updateFuelType(Long id, String name);

    List<FuelType> getAllFuelTypes();

    FuelType getFuelType(Long id);

    // ---------------------- Gearbox --------------------

    Gearbox createGearbox(String name);

    void deleteGearbox(Long id);

    Gearbox updateGearbox(Long id, String name);

    List<Gearbox> getAllGearboxes();

    Gearbox getGearbox(Long id);

    // ---------------------- Interior material --------------------

    InteriorMaterial createInteriorMaterial(String name);

    void deleteInteriorMaterial(Long id);

    InteriorMaterial updateInteriorMaterial(Long id, String name);

    List<InteriorMaterial> getAllInteriorMaterials();

    InteriorMaterial getInteriorMaterial(Long id);

    // ---------------------- Model --------------------

    Model createModel(String name);

    void deleteModel(Long id);

    Model updateModel(Long id, String name);

    List<Model> getAllModels();

    List<Model> getAllModelsByBrand(Long brand_id);

    Model getModel(Long id);

    // ---------------------- Tires season --------------------

    TiresSeason createTiresSeason(String name);

    void deleteTiresSeason(Long id);

    TiresSeason updateTiresSeason(Long id, String name);

    List<TiresSeason> getAllTiresSeasons();

    TiresSeason getTiresSeason(Long id);
}
