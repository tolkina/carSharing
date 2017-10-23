package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.TechnicalParameterDto;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.modelAdmin.*;
import com.exposit.carsharing.repositoryAdmin.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ModelMapper modelMapper;

    public AdminServiceImpl(BodyTypeRepository bodyTypeRepository,
                            BrandRepository brandRepository,
                            ColorRepository colorRepository,
                            DriveUnitRepository driveUnitRepository,
                            FuelTypeRepository fuelTypeRepository,
                            GearboxRepository gearboxRepository,
                            InteriorMaterialRepository interiorMaterialRepository,
                            ModelRepository modelRepository,
                            TiresSeasonRepository tiresSeasonRepository) {
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
    public BodyType createBodyType(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkBodyTypeNameUsed(technicalParameterDto.getName());
        BodyType bodyType = modelMapper.map(technicalParameterDto, BodyType.class);
        bodyTypeRepository.save(bodyType);
        return bodyType;
    }

    @Override
    public void deleteBodyType(Long id) throws EntityNotFoundException {
        bodyTypeRepository.delete(getBodyType(id));
    }

    @Override
    public BodyType updateBodyType(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        BodyType bodyType = getBodyType(id);
        checkBodyTypeNameUsed(technicalParameterDto.getName());
        bodyType.setName(technicalParameterDto.getName());
        return bodyTypeRepository.save(bodyType);
    }

    @Override
    public List<BodyType> getAllBodyTypes() {
        return bodyTypeRepository.findAll();
    }

    @Override
    public BodyType getBodyType(Long id) throws EntityNotFoundException {
        BodyType bodyType = bodyTypeRepository.findOne(id);
        if (bodyType == null) {
            throw new EntityNotFoundException("Body type", id);
        }
        return bodyType;
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
    public Brand createBrand(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkBrandNameUsed(technicalParameterDto.getName());
        Brand brand = modelMapper.map(technicalParameterDto, Brand.class);
        brandRepository.save(brand);
        return brand;
    }

    @Override
    public void deleteBrand(Long id) throws EntityNotFoundException {
        modelRepository.delete(getAllModelsByBrand(id));
        brandRepository.delete(getBrand(id));
    }

    @Override
    public Brand updateBrand(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        Brand brand = getBrand(id);
        checkBrandNameUsed(technicalParameterDto.getName());
        brand.setName(technicalParameterDto.getName());
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long id) throws EntityNotFoundException {
        Brand brand = brandRepository.findById(id);
        if (brand == null) {
            throw new EntityNotFoundException("Brand", id);
        }
        return brand;
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
    public Color createColor(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkColorNameUsed(technicalParameterDto.getName());
        Color color = modelMapper.map(technicalParameterDto, Color.class);
        colorRepository.save(color);
        return color;
    }

    @Override
    public void deleteColor(Long id) throws EntityNotFoundException {
        colorRepository.delete(getColor(id));

    }

    @Override
    public Color updateColor(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        Color color = getColor(id);
        checkColorNameUsed(technicalParameterDto.getName());
        color.setName(technicalParameterDto.getName());
        return colorRepository.save(color);
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public Color getColor(Long id) throws EntityNotFoundException {
        Color color = colorRepository.findOne(id);
        if (color == null) {
            throw new EntityNotFoundException("Color", id);
        }
        return color;
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
    public DriveUnit createDriveUnit(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkDriveUnitNameUsed(technicalParameterDto.getName());
        DriveUnit driveUnit = modelMapper.map(technicalParameterDto, DriveUnit.class);
        driveUnitRepository.save(driveUnit);
        return driveUnit;
    }

    @Override
    public void deleteDriveUnit(Long id) throws EntityNotFoundException {
        driveUnitRepository.delete(getDriveUnit(id));
    }

    @Override
    public DriveUnit updateDriveUnit(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        DriveUnit driveUnit = getDriveUnit(id);
        checkDriveUnitNameUsed(technicalParameterDto.getName());
        driveUnit.setName(technicalParameterDto.getName());
        return driveUnitRepository.save(driveUnit);
    }

    @Override
    public List<DriveUnit> getAllDriveUnits() {
        return driveUnitRepository.findAll();
    }

    @Override
    public DriveUnit getDriveUnit(Long id) throws EntityNotFoundException {
        DriveUnit driveUnit = driveUnitRepository.findOne(id);
        if (driveUnit == null) {
            throw new EntityNotFoundException("Drive unit", id);
        }
        return driveUnit;
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
    public FuelType createFuelType(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkFuelTypeNameUsed(technicalParameterDto.getName());
        FuelType fuelType = modelMapper.map(technicalParameterDto, FuelType.class);
        fuelTypeRepository.save(fuelType);
        return fuelType;
    }

    @Override
    public void deleteFuelType(Long id) throws EntityNotFoundException {
        fuelTypeRepository.delete(getFuelType(id));
    }

    @Override
    public FuelType updateFuelType(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        FuelType fuelType = getFuelType(id);
        checkFuelTypeNameUsed(technicalParameterDto.getName());
        fuelType.setName(technicalParameterDto.getName());
        return fuelTypeRepository.save(fuelType);
    }

    @Override
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }

    @Override
    public FuelType getFuelType(Long id) throws EntityNotFoundException {
        FuelType fuelType = fuelTypeRepository.findOne(id);
        if (fuelType == null) {
            throw new EntityNotFoundException("Fuel type", id);
        }
        return fuelType;
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
    public Gearbox createGearbox(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkGearboxNameUsed(technicalParameterDto.getName());
        Gearbox gearbox = modelMapper.map(technicalParameterDto, Gearbox.class);
        gearboxRepository.save(gearbox);
        return gearbox;
    }

    @Override
    public void deleteGearbox(Long id) throws EntityNotFoundException {
        gearboxRepository.delete(getGearbox(id));
    }

    @Override
    public Gearbox updateGearbox(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        Gearbox gearbox = getGearbox(id);
        checkGearboxNameUsed(technicalParameterDto.getName());
        gearbox.setName(technicalParameterDto.getName());
        return gearboxRepository.save(gearbox);
    }

    @Override
    public List<Gearbox> getAllGearboxes() {
        return gearboxRepository.findAll();
    }

    @Override
    public Gearbox getGearbox(Long id) throws EntityNotFoundException {
        Gearbox gearbox = gearboxRepository.findOne(id);
        if (gearbox == null) {
            throw new EntityNotFoundException("Gearbox", id);
        }
        return gearbox;
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
    public InteriorMaterial createInteriorMaterial(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkInteriorMaterialNameUsed(technicalParameterDto.getName());
        InteriorMaterial interiorMaterial = modelMapper.map(technicalParameterDto, InteriorMaterial.class);
        interiorMaterialRepository.save(interiorMaterial);
        return interiorMaterial;
    }

    @Override
    public void deleteInteriorMaterial(Long id) throws EntityNotFoundException {
        interiorMaterialRepository.delete(getInteriorMaterial(id));
    }

    @Override
    public InteriorMaterial updateInteriorMaterial(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        InteriorMaterial interiorMaterial = getInteriorMaterial(id);
        checkInteriorMaterialNameUsed(technicalParameterDto.getName());
        interiorMaterial.setName(technicalParameterDto.getName());
        return interiorMaterialRepository.save(interiorMaterial);
    }

    @Override
    public List<InteriorMaterial> getAllInteriorMaterials() {
        return interiorMaterialRepository.findAll();
    }

    @Override
    public InteriorMaterial getInteriorMaterial(Long id) throws EntityNotFoundException {
        InteriorMaterial interiorMaterial = interiorMaterialRepository.findOne(id);
        if (interiorMaterial == null) {
            throw new EntityNotFoundException("Interior material", id);
        }
        return interiorMaterial;
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
    public Model createModel(Long branId, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        Brand brand = getBrand(branId);
        checkModelNameUsed(technicalParameterDto.getName());
        Model model = modelMapper.map(technicalParameterDto, Model.class);
        model.setBrand(brand);
        modelRepository.save(model);
        return model;
    }

    @Override
    public void deleteModel(Long id) throws EntityNotFoundException {
        getModel(id);
        modelRepository.delete(id);
    }

    @Override
    public Model updateModel(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        Model model = getModel(id);
        checkModelNameUsed(technicalParameterDto.getName());
        model.setName(technicalParameterDto.getName());
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public List<Model> getAllModelsByBrand(Long brand_id) throws EntityNotFoundException {
        return modelRepository.findAllByBrand(getBrand(brand_id));
    }

    @Override
    public Model getModel(Long id) throws EntityNotFoundException {
        Model model = modelRepository.findOne(id);
        if (model == null) {
            throw new EntityNotFoundException("Model", id);
        }
        return model;
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
    public TiresSeason createTiresSeason(TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException {
        checkTiresSeasonNameUsed(technicalParameterDto.getName());
        TiresSeason tiresSeason = new TiresSeason();
        tiresSeason.setName(technicalParameterDto.getName());
        tiresSeasonRepository.save(tiresSeason);
        return tiresSeason;
    }

    @Override
    public void deleteTiresSeason(Long id) throws EntityNotFoundException {
        tiresSeasonRepository.delete(getTiresSeason(id));
    }

    @Override
    public TiresSeason updateTiresSeason(Long id, TechnicalParameterDto technicalParameterDto) throws EntityAlreadyExistException, EntityNotFoundException {
        TiresSeason tiresSeason = getTiresSeason(id);
        checkTiresSeasonNameUsed(technicalParameterDto.getName());
        tiresSeason.setName(technicalParameterDto.getName());
        return tiresSeasonRepository.save(tiresSeason);
    }

    @Override
    public List<TiresSeason> getAllTiresSeasons() {
        return tiresSeasonRepository.findAll();
    }

    @Override
    public TiresSeason getTiresSeason(Long id) throws EntityNotFoundException {
        TiresSeason tiresSeason = tiresSeasonRepository.findOne(id);
        if (tiresSeason == null) {
            throw new EntityNotFoundException("Tires season", id);
        }
        return tiresSeason;
    }
}
