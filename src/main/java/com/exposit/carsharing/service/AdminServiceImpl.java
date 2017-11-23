package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.*;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.ConfirmProfileException;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    private final ProfileRepository profileRepository;
    private final ConfirmationRepository confirmationRepository;

    public AdminServiceImpl(BodyTypeRepository bodyTypeRepository, BrandRepository brandRepository,
                            ColorRepository colorRepository, DriveUnitRepository driveUnitRepository,
                            FuelTypeRepository fuelTypeRepository, GearboxRepository gearboxRepository,
                            InteriorMaterialRepository interiorMaterialRepository, ModelRepository modelRepository,
                            TiresSeasonRepository tiresSeasonRepository, ModelMapper modelMapper,
                            ProfileService profileService, RoleRepository roleRepository,
                            ProfileRepository profileRepository, ConfirmationRepository confirmationRepository) {
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
        this.profileRepository = profileRepository;
        this.confirmationRepository = confirmationRepository;
    }

    @Override
    public void checkAdmin(Long principalId) throws EntityNotFoundException, PrivilegeException {
        Collection<Role> roles = profileService.getProfile(principalId).getRoles();
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
    public Page<CarParameterResponse> getAllBodyTypes(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<BodyType> carParameterPage = bodyTypeRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
        return mapToBrandResponse(brand);
    }

    @Override
    public void deleteBrand(Long id) throws EntityNotFoundException {
        brandRepository.delete(getBrand(id));
    }

    @Override
    public BrandResponse updateBrand(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        Brand brand = getBrand(id);
        checkBrandNameUsed(carParameterRequest.getName());
        brand.setName(carParameterRequest.getName());
        return mapToBrandResponse(brand);
    }

    @Override
    public Page<BrandResponse> getAllBrands(PageParametersRequest pageParametersRequest) {
        PageRequest pageRequest = getPageRequest(pageParametersRequest);
        List<BrandResponse> brands = new ArrayList<>();
        Page<Brand> brandPage = brandRepository.findAll(pageRequest);
        brandPage.getContent().forEach(brand -> brands.add(mapToBrandResponse(brand)));
        return new PageImpl<>(brands, pageRequest, brandPage.getTotalElements());
    }

    private PageRequest getPageRequest(PageParametersRequest pageParametersRequest) {
        if (pageParametersRequest.getSortType() == SortType.BY_ID_REVERSE) {
            return new PageRequest(pageParametersRequest.getPage() - 1,
                    pageParametersRequest.getSize(), Sort.Direction.DESC, "id");
        }
        if (pageParametersRequest.getSortType() == SortType.BY_NAME) {
            return new PageRequest(pageParametersRequest.getPage() - 1,
                    pageParametersRequest.getSize(), Sort.Direction.ASC, "name");
        }
        if (pageParametersRequest.getSortType() == SortType.BY_NAME_REVERSE) {
            return new PageRequest(pageParametersRequest.getPage() - 1,
                    pageParametersRequest.getSize(), Sort.Direction.DESC, "name");
        }
        return new PageRequest(pageParametersRequest.getPage() - 1,
                pageParametersRequest.getSize(), Sort.Direction.ASC, "id");
    }

    @Override
    public BrandResponse getBrandResponse(Long id) throws EntityNotFoundException {
        return mapToBrandResponse(getBrand(id));
    }

    private Brand getBrand(Long id) throws EntityNotFoundException {
        Brand brand = brandRepository.findOne(id);
        if (brand == null) {
            throw new EntityNotFoundException("Brand", id);
        }
        return brand;
    }

    private BrandResponse mapToBrandResponse(Brand brand) {
        BrandResponse brandResponse = modelMapper.map(brand, BrandResponse.class);
        brandResponse.setCountOfModels(brand.getModels().size());
        return brandResponse;
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
    public Page<CarParameterResponse> getAllColors(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<Color> carParameterPage = colorRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
    public Page<CarParameterResponse> getAllDriveUnits(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<DriveUnit> carParameterPage = driveUnitRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
    public Page<CarParameterResponse> getAllFuelTypes(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<FuelType> carParameterPage = fuelTypeRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
    public Page<CarParameterResponse> getAllGearboxes(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<Gearbox> carParameterPage = gearboxRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
    public Page<CarParameterResponse> getAllInteriorMaterials(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<InteriorMaterial> carParameterPage = interiorMaterialRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
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
        Brand brand = getBrand(branId);
        checkModelNameUsed(carParameterRequest.getName());
        Model model = modelMapper.map(carParameterRequest, Model.class);
        model.setBrand(brand);
        modelRepository.save(model);
        return modelMapper.map(model, ModelResponse.class);
    }

    @Override
    public void deleteModel(Long id) throws EntityNotFoundException {
        modelRepository.delete(getModel(id));
    }

    @Override
    public ModelResponse updateModel(Long id, CarParameterRequest carParameterRequest) throws EntityAlreadyExistException, EntityNotFoundException {
        Model model = getModel(id);
        checkModelNameUsed(carParameterRequest.getName());
        model.setName(carParameterRequest.getName());
        return modelMapper.map(model, ModelResponse.class);
    }

    @Override
    public Page<ModelResponse> getAllModels(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<ModelResponse> models = new ArrayList<>();
        Page<Model> carParameterPage = modelRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(model -> models.add(modelMapper.map(model, ModelResponse.class)));
        return new PageImpl<>(models, pageRequest, carParameterPage.getTotalElements());
    }

    @Override
    public Page<CarParameterResponse> getAllModelsByBrand(Long brandId, Integer page, Integer size) throws EntityNotFoundException {
        Brand brand = getBrand(brandId);
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> models = new ArrayList<>();
        Page<Model> modelPage = modelRepository.findAllByBrand(brand, pageRequest);
        modelPage.getContent().forEach(model -> models.add(modelMapper.map(model, CarParameterResponse.class)));
        return new PageImpl<>(models, pageRequest, modelPage.getTotalElements());
    }

    @Override
    public ModelResponse getModelResponse(Long id) throws EntityNotFoundException {
        return modelMapper.map(getModel(id), ModelResponse.class);
    }

    private Model getModel(Long id) throws EntityNotFoundException {
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
    public Page<CarParameterResponse> getAllTiresSeasons(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<CarParameterResponse> carParameters = new ArrayList<>();
        Page<TiresSeason> carParameterPage = tiresSeasonRepository.findAll(pageRequest);
        carParameterPage.getContent()
                .forEach(carParameter -> carParameters.add(modelMapper.map(carParameter, CarParameterResponse.class)));
        return new PageImpl<>(carParameters, pageRequest, carParameterPage.getTotalElements());
    }

    @Override
    public CarParameterResponse getTiresSeason(Long id) throws EntityNotFoundException {
        TiresSeason tiresSeason = tiresSeasonRepository.findOne(id);
        if (tiresSeason == null) {
            throw new EntityNotFoundException("Tires season", id);
        }
        return modelMapper.map(tiresSeason, CarParameterResponse.class);
    }

    // ------------------------- Confirm Profile --------------------

    @Override
    public Page<ConfirmProfileResponse> getProfilesToConfirm(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<ConfirmProfileResponse> profiles = new ArrayList<>();
        Page<Profile> profilePage = profileRepository.findByConfirmProfile(ConfirmProfile.CHECK, pageRequest);
        profilePage.getContent().forEach(profile -> {
            ConfirmProfileResponse confirmProfileResponse = modelMapper.map(profile, ConfirmProfileResponse.class);
            confirmProfileResponse.setPassportDataResponse(
                    modelMapper.map(profile.getPassportData(), PassportDataResponse.class));
            confirmProfileResponse.setDriverLicenseResponse(
                    modelMapper.map(profile.getDriverLicense(), DriverLicenseResponse.class));
            profiles.add(confirmProfileResponse);
        });
        return new PageImpl<>(profiles, pageRequest, profilePage.getTotalElements());
    }

    @Override
    public ConfirmationResponse setConfirmProfileYes(Long profileId) throws EntityNotFoundException,
            PrivilegeException, ConfirmProfileException {
        return confirmProfile(profileId, ConfirmProfile.YES);
    }

    @Override
    public ConfirmationResponse setConfirmProfileNo(Long profileId) throws EntityNotFoundException,
            PrivilegeException, ConfirmProfileException {
        return confirmProfile(profileId, ConfirmProfile.NO);
    }

    @Override
    public Page<ConfirmationResponse> getConfirmations(Integer page, Integer size) {
        Pageable pageRequest = new PageRequest(page - 1, size);
        List<ConfirmationResponse> confirmations = new ArrayList<>();
        Page<Confirmation> confirmationPage = confirmationRepository.findAll(pageRequest);
        confirmationPage.getContent()
                .forEach(confirmation -> confirmations.add(modelMapper.map(confirmation, ConfirmationResponse.class)));
        return new PageImpl<>(confirmations, pageRequest, confirmationPage.getTotalElements());
    }

    private ConfirmationResponse confirmProfile(Long profileId, ConfirmProfile confirmProfile)
            throws EntityNotFoundException, ConfirmProfileException {
        Profile profile = profileService.getProfile(profileId);
        if (!profile.getConfirmProfile().equals(ConfirmProfile.CHECK)) {
            throw new ConfirmProfileException("This profile has not been submitted for confirm");
        }
        profile.setConfirmProfile(confirmProfile);
        return createConfirmation(profile);
    }

    private ConfirmationResponse createConfirmation(Profile profile) {
        Confirmation confirmation = new Confirmation();

        confirmation.setConfirmProfile(profile.getConfirmProfile());
        confirmation.setDateConfirm(LocalDateTime.now());
        confirmation.setProfile(profile);

        confirmation.setPassportFirstName(profile.getPassportData().getFirstName());
        confirmation.setPassportLastName(profile.getPassportData().getLastName());
        confirmation.setPassportMiddleName(profile.getPassportData().getMiddleName());
        confirmation.setPassportSeriesAndNumber(profile.getPassportData().getSeriesAndNumber());
        confirmation.setPassportPersonalNumber(profile.getPassportData().getPersonalNumber());
        confirmation.setPassportDateOfIssue(profile.getPassportData().getDateOfIssue());
        confirmation.setPassportPlaceOfIssue(profile.getPassportData().getPlaceOfIssue());
        confirmation.setPassportValidUntil(profile.getPassportData().getValidUntil());
        confirmation.setPassportRegistrationPhotoUrl(profile.getPassportData().getRegistrationPhotoUrl());
        confirmation.setPassportPhotoUrl(profile.getPassportData().getPhotoUrl());

        confirmation.setDriverLicenseSeriesAndNumber(profile.getDriverLicense().getSeriesAndNumber());
        confirmation.setDriverLicenseCategory(profile.getDriverLicense().getCategory());
        confirmation.setDriverLicenseFrontSideImageUrl(profile.getDriverLicense().getFrontSideImageUrl());
        confirmation.setDriverLicenseBackSideImageUrl(profile.getDriverLicense().getBackSideImageUrl());

        confirmationRepository.save(confirmation);
        return mapToConfirmationResponse(confirmation);
    }

    private ConfirmationResponse mapToConfirmationResponse(Confirmation confirmation) {
        ConfirmationResponse confirmationResponse = modelMapper.map(confirmation, ConfirmationResponse.class);
        confirmationResponse.setProfileId(confirmation.getProfile().getId());
        confirmationResponse.setProfileLogin(confirmation.getProfile().getLogin());
        return confirmationResponse;
    }
}
