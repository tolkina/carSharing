package com.exposit.carsharing.service;

import com.exposit.carsharing.modelAdmin.*;
import com.exposit.carsharing.repositoryAdmin.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    private final BodyTypeRepository bodyTypeRepository;
    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final DriveUnitRepository driveUnitRepository;
    private final FuelTypeRepository fuelTypeRepository;
    private final GearboxRepository gearboxRepository;
    private final InteriorMaterialRepository interiorMaterialRepository;
    private final ModelRepository modelRepository;
    private final TiresSeasonRepository tiresSeasonRepository;

    public AdminServiceImpl(BodyTypeRepository bodyTypeRepository, BrandRepository brandRepository, ColorRepository colorRepository, DriveUnitRepository driveUnitRepository, FuelTypeRepository fuelTypeRepository, GearboxRepository gearboxRepository, InteriorMaterialRepository interiorMaterialRepository, ModelRepository modelRepository, TiresSeasonRepository tiresSeasonRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.driveUnitRepository = driveUnitRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.gearboxRepository = gearboxRepository;
        this.interiorMaterialRepository = interiorMaterialRepository;
        this.modelRepository = modelRepository;
        this.tiresSeasonRepository = tiresSeasonRepository;
    }

    @Override
    public void createBodyType(String name) {

    }

    @Override
    public void deleteBodyType(Long id) {

    }

    @Override
    public void updateBodyType(Long id, String name) {

    }

    @Override
    public List<BodyType> getAllBodyTypes() {
        return null;
    }

    @Override
    public BodyType getBodyType(Long id) {
        return null;
    }

    @Override
    public void createBrand(String name) {

    }

    @Override
    public void deleteBrand(Long id) {

    }

    @Override
    public void updateBrand(Long id, String name) {

    }

    @Override
    public List<Brand> getAllBrands() {
        return null;
    }

    @Override
    public Brand getBrand(Long id) {
        return null;
    }

    @Override
    public void createColor(String name) {

    }

    @Override
    public void deleteColor(Long id) {

    }

    @Override
    public void updateColor(Long id, String name) {

    }

    @Override
    public List<Color> getAllColors() {
        return null;
    }

    @Override
    public Color getColor(Long id) {
        return null;
    }

    @Override
    public void createDriveUnit(String name) {

    }

    @Override
    public void deleteDriveUnit(Long id) {

    }

    @Override
    public void updateDriveUnit(Long id, String name) {

    }

    @Override
    public List<DriveUnit> getAllDriveUnits() {
        return null;
    }

    @Override
    public DriveUnit getDriveUnit(Long id) {
        return null;
    }

    @Override
    public void createFuelType(String name) {

    }

    @Override
    public void deleteFuelType(Long id) {

    }

    @Override
    public void updateFuelType(Long id, String name) {

    }

    @Override
    public List<FuelType> getAllFuelTypes() {
        return null;
    }

    @Override
    public FuelType getFuelType(Long id) {
        return null;
    }

    @Override
    public void createGearbox(String name) {

    }

    @Override
    public void deleteGearbox(Long id) {

    }

    @Override
    public void updateGearbox(Long id, String name) {

    }

    @Override
    public List<Gearbox> getAllGearboxes() {
        return null;
    }

    @Override
    public Gearbox getGearbox(Long id) {
        return null;
    }

    @Override
    public void createInteriorMaterial(String name) {

    }

    @Override
    public void deleteInteriorMaterial(Long id) {

    }

    @Override
    public void updateInteriorMaterial(Long id, String name) {

    }

    @Override
    public List<InteriorMaterial> getAllInteriorMaterials() {
        return null;
    }

    @Override
    public InteriorMaterial getInteriorMaterial(Long id) {
        return null;
    }

    @Override
    public void createModel(String name) {

    }

    @Override
    public void deleteModel(Long id) {

    }

    @Override
    public void updateModel(Long id, String name) {

    }

    @Override
    public List<Model> getAllModels() {
        return null;
    }

    @Override
    public List<Model> getAllModelsByBrand(Long brand_id) {
        return null;
    }

    @Override
    public Model getModel(Long id) {
        return null;
    }

    @Override
    public void createTiresSeason(String name) {

    }

    @Override
    public void deleteTiresSeason(Long id) {

    }

    @Override
    public void updateTiresSeason(Long id, String name) {

    }

    @Override
    public List<TiresSeason> getAllTiresSeasons() {
        return null;
    }

    @Override
    public TiresSeason getTiresSeason(Long id) {
        return null;
    }
}
