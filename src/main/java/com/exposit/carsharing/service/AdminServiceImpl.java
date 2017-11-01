package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.*;
import com.exposit.carsharing.dto.BrandResponse;
import com.exposit.carsharing.dto.CarParameterRequest;
import com.exposit.carsharing.dto.CarParameterResponse;
import com.exposit.carsharing.dto.ModelResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
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
    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final RoleRepository roleRepository;

    @Autowired
    public AdminServiceImpl(BodyTypeRepository bodyTypeRepository,
                            BrandRepository brandRepository,
                            ColorRepository colorRepository,
                            DriveUnitRepository driveUnitRepository,
                            FuelTypeRepository fuelTypeRepository,
                            GearboxRepository gearboxRepository,
                            InteriorMaterialRepository interiorMaterialRepository,
                            ModelRepository modelRepository,
                            TiresSeasonRepository tiresSeasonRepository,
                            ModelMapper modelMapper, ProfileService profileService, RoleRepository roleRepository) {
        this.bodyTypeRepository = bodyTypeRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.driveUnitRepository = driveUnitRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.gearboxRepository = gearboxRepository;
        this.interiorMaterialRepository = interiorMaterialRepository;
        this.modelRepository = modelRepository;
        this.tiresSeasonRepository = tiresSeasonRepository;
        this.modelMapper = modelMapper;
        this.profileService = profileService;
        this.roleRepository = roleRepository;
    }

    @Override
    public void checkAdmin(Long principalId) throws EntityNotFoundException, PrivilegeException {
        Collection<Role> roles = profileService.get(principalId).getRoles();
        if (!roles.contains(roleRepository.findByRole("ROLE_ADMIN"))) {
            throw new PrivilegeException();
        }
    }

    @Override
    public void checkBodyTypeExist(String name) throws EntityNotFoundException {
        if (name != null && bodyTypeRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Body type", name);
        }
    }

    @Override
    public void checkBodyTypeNameUsed(String name) throws EntityAlreadyExistException {
        if (bodyTypeRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createBodyType(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkBodyTypeNameUsed(carParameterRequest.getName());
        BodyType bodyType = modelMapper.map(carParameterRequest, BodyType.class);
        bodyTypeRepository.save(bodyType);
        return modelMapper.map(bodyType, CarParameterResponse.class);
    }

    @Override
    public void deleteBodyType(Long id) throws EntityNotFoundException {
        getBodyType(id);
        bodyTypeRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateBodyType(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse bodyType = getBodyType(id);
        checkBodyTypeNameUsed(carParameterRequest.getName());
        bodyType.setName(carParameterRequest.getName());
        bodyTypeRepository.save(modelMapper.map(bodyType, BodyType.class));
        return bodyType;
    }

    @Override
    public List<CarParameterResponse> getAllBodyTypes() {
        List<CarParameterResponse> bodyTypes = new ArrayList<>();
        bodyTypeRepository.findAll().forEach(bodyType -> bodyTypes.add(modelMapper.map(bodyType, CarParameterResponse.class)));
        return bodyTypes;
    }

    @Override
    public CarParameterResponse getBodyType(Long id) throws EntityNotFoundException {
        BodyType bodyType = bodyTypeRepository.findOne(id);
        if (bodyType == null) {
            throw new EntityNotFoundException("Body type", id);
        }
        return modelMapper.map(bodyType, CarParameterResponse.class);
    }

    @Override
    public void checkBrandExist(String name) throws EntityNotFoundException {
        if (name != null && brandRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Brand", name);
        }
    }

    @Override
    public void checkBrandAndModelExist(String brand, String model) throws EntityNotFoundException, PrivilegeException {
        checkBrandExist(brand);
        checkModelExist(model);
        if (model != null && brand != null &&
                modelRepository.findByNameAndBrand(model, brandRepository.findByName(brand)) == null) {
            throw new PrivilegeException(String.format("Model %s don't belong to brand %s", model, brand));
        }
    }

    @Override
    public void checkBrandNameUsed(String name) throws EntityAlreadyExistException {
        if (brandRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public BrandResponse createBrand(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkBrandNameUsed(carParameterRequest.getName());
        Brand brand = modelMapper.map(carParameterRequest, Brand.class);
        brandRepository.save(brand);
        return modelMapper.map(brand, BrandResponse.class);
    }

    @Override
    public void deleteBrand(Long id) throws EntityNotFoundException {
        getBrand(id);
        brandRepository.delete(id);
    }

    @Override
    public BrandResponse updateBrand(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        BrandResponse brand = getBrand(id);
        checkBrandNameUsed(carParameterRequest.getName());
        brand.setName(carParameterRequest.getName());
        brandRepository.save(modelMapper.map(brand, Brand.class));
        return brand;
    }

    @Override
    public List<BrandResponse> getAllBrands() {
        List<BrandResponse> brands = new ArrayList<>();
        brandRepository.findAll().forEach(brand -> brands.add(modelMapper.map(brand, BrandResponse.class)));
        return brands;
    }

    @Override
    public BrandResponse getBrand(Long id) throws EntityNotFoundException {
        Brand brand = brandRepository.findById(id);
        if (brand == null) {
            throw new EntityNotFoundException("Brand", id);
        }
        return modelMapper.map(brand, BrandResponse.class);
    }

    @Override
    public void checkColorExist(String name) throws EntityNotFoundException {
        if (name != null && colorRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Color", name);
        }
    }

    @Override
    public void checkColorNameUsed(String name) throws EntityAlreadyExistException {
        if (colorRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createColor(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkColorNameUsed(carParameterRequest.getName());
        Color color = modelMapper.map(carParameterRequest, Color.class);
        colorRepository.save(color);
        return modelMapper.map(color, CarParameterResponse.class);
    }

    @Override
    public void deleteColor(Long id) throws EntityNotFoundException {
        getColor(id);
        colorRepository.delete(id);

    }

    @Override
    public CarParameterResponse updateColor(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse color = getColor(id);
        checkColorNameUsed(carParameterRequest.getName());
        color.setName(carParameterRequest.getName());
        colorRepository.save(modelMapper.map(color, Color.class));
        return color;
    }

    @Override
    public List<CarParameterResponse> getAllColors() {
        List<CarParameterResponse> colors = new ArrayList<>();
        colorRepository.findAll().forEach(color -> colors.add(modelMapper.map(color, CarParameterResponse.class)));
        return colors;
    }

    @Override
    public CarParameterResponse getColor(Long id) throws EntityNotFoundException {
        Color color = colorRepository.findOne(id);
        if (color == null) {
            throw new EntityNotFoundException("Color", id);
        }
        return modelMapper.map(color, CarParameterResponse.class);
    }

    @Override
    public void checkDriveUnitExist(String name) throws EntityNotFoundException {
        if (name != null && driveUnitRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Drive unit", name);
        }
    }

    @Override
    public void checkDriveUnitNameUsed(String name) throws EntityAlreadyExistException {
        if (driveUnitRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createDriveUnit(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkDriveUnitNameUsed(carParameterRequest.getName());
        DriveUnit driveUnit = modelMapper.map(carParameterRequest, DriveUnit.class);
        driveUnitRepository.save(driveUnit);
        return modelMapper.map(driveUnit, CarParameterResponse.class);
    }

    @Override
    public void deleteDriveUnit(Long id) throws EntityNotFoundException {
        getDriveUnit(id);
        driveUnitRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateDriveUnit(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse driveUnit = getDriveUnit(id);
        checkDriveUnitNameUsed(carParameterRequest.getName());
        driveUnit.setName(carParameterRequest.getName());
        driveUnitRepository.save(modelMapper.map(driveUnit, DriveUnit.class));
        return driveUnit;
    }

    @Override
    public List<CarParameterResponse> getAllDriveUnits() {
        List<CarParameterResponse> driveUnits = new ArrayList<>();
        driveUnitRepository.findAll().forEach(driveUnit -> driveUnits.add(modelMapper.map(driveUnit, CarParameterResponse.class)));
        return driveUnits;
    }

    @Override
    public CarParameterResponse getDriveUnit(Long id) throws EntityNotFoundException {
        DriveUnit driveUnit = driveUnitRepository.findOne(id);
        if (driveUnit == null) {
            throw new EntityNotFoundException("Drive unit", id);
        }
        return modelMapper.map(driveUnit, CarParameterResponse.class);
    }

    @Override
    public void checkFuelTypeExist(String name) throws EntityNotFoundException {
        if (name != null && fuelTypeRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Fuel type", name);
        }
    }

    @Override
    public void checkFuelTypeNameUsed(String name) throws EntityAlreadyExistException {
        if (fuelTypeRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createFuelType(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkFuelTypeNameUsed(carParameterRequest.getName());
        FuelType fuelType = modelMapper.map(carParameterRequest, FuelType.class);
        fuelTypeRepository.save(fuelType);
        return modelMapper.map(fuelType, CarParameterResponse.class);
    }

    @Override
    public void deleteFuelType(Long id) throws EntityNotFoundException {
        getFuelType(id);
        fuelTypeRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateFuelType(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse fuelType = getFuelType(id);
        checkFuelTypeNameUsed(carParameterRequest.getName());
        fuelType.setName(carParameterRequest.getName());
        fuelTypeRepository.save(modelMapper.map(fuelType, FuelType.class));
        return fuelType;
    }

    @Override
    public List<CarParameterResponse> getAllFuelTypes() {
        List<CarParameterResponse> fuelTypes = new ArrayList<>();
        fuelTypeRepository.findAll().forEach(fuelType -> fuelTypes.add(modelMapper.map(fuelType, CarParameterResponse.class)));
        return fuelTypes;
    }

    @Override
    public CarParameterResponse getFuelType(Long id) throws EntityNotFoundException {
        FuelType fuelType = fuelTypeRepository.findOne(id);
        if (fuelType == null) {
            throw new EntityNotFoundException("Fuel type", id);
        }
        return modelMapper.map(fuelType, CarParameterResponse.class);
    }

    @Override
    public void checkGearboxExist(String name) throws EntityNotFoundException {
        if (name != null && gearboxRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Gearbox", name);
        }
    }

    @Override
    public void checkGearboxNameUsed(String name) throws EntityAlreadyExistException {
        if (gearboxRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createGearbox(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkGearboxNameUsed(carParameterRequest.getName());
        Gearbox gearbox = modelMapper.map(carParameterRequest, Gearbox.class);
        gearboxRepository.save(gearbox);
        return modelMapper.map(gearbox, CarParameterResponse.class);
    }

    @Override
    public void deleteGearbox(Long id) throws EntityNotFoundException {
        getGearbox(id);
        gearboxRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateGearbox(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse gearbox = getGearbox(id);
        checkGearboxNameUsed(carParameterRequest.getName());
        gearbox.setName(carParameterRequest.getName());
        gearboxRepository.save(modelMapper.map(gearbox, Gearbox.class));
        return gearbox;
    }

    @Override
    public List<CarParameterResponse> getAllGearboxes() {
        List<CarParameterResponse> gearboxes = new ArrayList<>();
        gearboxRepository.findAll().forEach(gearbox -> gearboxes.add(modelMapper.map(gearbox, CarParameterResponse.class)));
        return gearboxes;
    }

    @Override
    public CarParameterResponse getGearbox(Long id) throws EntityNotFoundException {
        Gearbox gearbox = gearboxRepository.findOne(id);
        if (gearbox == null) {
            throw new EntityNotFoundException("Gearbox", id);
        }
        return modelMapper.map(gearbox, CarParameterResponse.class);
    }

    @Override
    public void checkInteriorMaterialExist(String name) throws EntityNotFoundException {
        if (name != null && interiorMaterialRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Interior type", name);
        }
    }

    @Override
    public void checkInteriorMaterialNameUsed(String name) throws EntityAlreadyExistException {
        if (interiorMaterialRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createInteriorMaterial(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkInteriorMaterialNameUsed(carParameterRequest.getName());
        InteriorMaterial interiorMaterial = modelMapper.map(carParameterRequest, InteriorMaterial.class);
        interiorMaterialRepository.save(interiorMaterial);
        return modelMapper.map(interiorMaterial, CarParameterResponse.class);
    }

    @Override
    public void deleteInteriorMaterial(Long id) throws EntityNotFoundException {
        getInteriorMaterial(id);
        interiorMaterialRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateInteriorMaterial(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse interiorMaterial = getInteriorMaterial(id);
        checkInteriorMaterialNameUsed(carParameterRequest.getName());
        interiorMaterial.setName(carParameterRequest.getName());
        interiorMaterialRepository.save(modelMapper.map(interiorMaterial, InteriorMaterial.class));
        return interiorMaterial;
    }

    @Override
    public List<CarParameterResponse> getAllInteriorMaterials() {
        List<CarParameterResponse> interiorMaterials = new ArrayList<>();
        interiorMaterialRepository.findAll().forEach(interiorMaterial ->
                interiorMaterials.add(modelMapper.map(interiorMaterial, CarParameterResponse.class)));
        return interiorMaterials;
    }

    @Override
    public CarParameterResponse getInteriorMaterial(Long id) throws EntityNotFoundException {
        InteriorMaterial interiorMaterial = interiorMaterialRepository.findOne(id);
        if (interiorMaterial == null) {
            throw new EntityNotFoundException("Interior material", id);
        }
        return modelMapper.map(interiorMaterial, CarParameterResponse.class);
    }

    @Override
    public void checkModelExist(String name) throws EntityNotFoundException {
        if (name != null && modelRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Model", name);
        }
    }

    @Override
    public void checkModelNameUsed(String name) throws EntityAlreadyExistException {
        if (modelRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public ModelResponse createModel(Long branId, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        Brand brand = modelMapper.map(getBrand(branId), Brand.class);
        checkModelNameUsed(carParameterRequest.getName());
        Model model = modelMapper.map(carParameterRequest, Model.class);
        model.setBrand(brand);
        modelRepository.save(model);
        return modelMapper.map(model, ModelResponse.class);
    }

    @Override
    public void deleteModel(Long id) throws EntityNotFoundException {
        getModel(id);
        modelRepository.delete(id);
    }

    @Override
    public ModelResponse updateModel(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        ModelResponse model = getModel(id);
        checkModelNameUsed(carParameterRequest.getName());
        model.setName(carParameterRequest.getName());
        modelRepository.save(modelMapper.map(model, Model.class));
        return model;
    }

    @Override
    public List<ModelResponse> getAllModels() {
        List<ModelResponse> models = new ArrayList<>();
        modelRepository.findAll().forEach(model -> models.add(modelMapper.map(model, ModelResponse.class)));
        return models;
    }

    @Override
    public List<CarParameterResponse> getAllModelsByBrand(Long brandId) throws EntityNotFoundException {
        List<CarParameterResponse> models = new ArrayList<>();
        modelRepository.findAllByBrand(modelMapper.map(getBrand(brandId), Brand.class))
                .forEach(model -> models.add(modelMapper.map(model, CarParameterResponse.class)));
        return models;
    }

    @Override
    public ModelResponse getModel(Long id) throws EntityNotFoundException {
        Model model = modelRepository.findOne(id);
        if (model == null) {
            throw new EntityNotFoundException("Model", id);
        }
        return modelMapper.map(model, ModelResponse.class);
    }

    @Override
    public void checkTiresSeasonExist(String name) throws EntityNotFoundException {
        if (name != null && tiresSeasonRepository.findByName(name) == null) {
            throw new EntityNotFoundException("Tires season", name);
        }
    }

    @Override
    public void checkTiresSeasonNameUsed(String name) throws EntityAlreadyExistException {
        if (tiresSeasonRepository.findByName(name) != null) {
            throw new EntityAlreadyExistException();
        }
    }

    @Override
    public CarParameterResponse createTiresSeason(CarParameterRequest carParameterRequest) throws EntityAlreadyExistException {
        checkTiresSeasonNameUsed(carParameterRequest.getName());
        TiresSeason tiresSeason = new TiresSeason();
        tiresSeason.setName(carParameterRequest.getName());
        tiresSeasonRepository.save(tiresSeason);
        return modelMapper.map(tiresSeason, CarParameterResponse.class);
    }

    @Override
    public void deleteTiresSeason(Long id) throws EntityNotFoundException {
        getTiresSeason(id);
        tiresSeasonRepository.delete(id);
    }

    @Override
    public CarParameterResponse updateTiresSeason(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        CarParameterResponse tiresSeason = getTiresSeason(id);
        checkTiresSeasonNameUsed(carParameterRequest.getName());
        tiresSeason.setName(carParameterRequest.getName());
        tiresSeasonRepository.save(modelMapper.map(tiresSeason, TiresSeason.class));
        return tiresSeason;
    }

    @Override
    public List<CarParameterResponse> getAllTiresSeasons() {
        List<CarParameterResponse> tiresSeasons = new ArrayList<>();
        tiresSeasonRepository.findAll().forEach(tiresSeason -> tiresSeasons.add(modelMapper.map(tiresSeason, CarParameterResponse.class)));
        return tiresSeasons;
    }

    @Override
    public CarParameterResponse getTiresSeason(Long id) throws EntityNotFoundException {
        TiresSeason tiresSeason = tiresSeasonRepository.findOne(id);
        if (tiresSeason == null) {
            throw new EntityNotFoundException("Tires season", id);
        }
        return modelMapper.map(tiresSeason, CarParameterResponse.class);
    }
}
