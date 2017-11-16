package com.exposit.carsharing.configuration;

import com.exposit.carsharing.domain.Role;
import com.exposit.carsharing.dto.CarParameterRequest;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.BrandRepository;
import com.exposit.carsharing.repository.RoleRepository;
import com.exposit.carsharing.service.AdminService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;
    private final AdminService adminService;
    private final BrandRepository brandRepository;
    private boolean alreadySetup = false;
    private boolean model = false;
    private Long brandId;

    public InitialDataLoader(RoleRepository roleRepository, AdminService adminService,
                             BrandRepository brandRepository) {
        this.roleRepository = roleRepository;
        this.adminService = adminService;
        this.brandRepository = brandRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) return;
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");
        createCarParameters();
        createBrandsAndModels();
        alreadySetup = true;
    }

    @Transactional
    void createRoleIfNotFound(String name) {
        Role role = roleRepository.findByRole(name);
        if (role == null) {
            roleRepository.save(new Role(name));
        }
    }

    private void createCarParameters() {
        try {
            Files.lines(Paths.get("src/main/resources/files/bodyType.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createBodyType(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/color.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createColor(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/driveUnit.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createDriveUnit(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/fuelType.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createFuelType(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/gearbox.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createGearbox(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/interiorMaterial.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createInteriorMaterial(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
            Files.lines(Paths.get("src/main/resources/files/tiresSeason.txt"), StandardCharsets.UTF_8)
                    .forEach(line -> {
                        try {
                            adminService.createTiresSeason(new CarParameterRequest(line));
                        } catch (EntityAlreadyExistException ignored) {
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBrandsAndModels() {
        try {
            Files.lines(Paths.get("src/main/resources/files/brand.txt"), StandardCharsets.UTF_8)
                    .forEach(this::ccc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ccc(String line) {
        if (line.equals("[")) {
            model = true;
            return;
        }
        if (line.equals("]")) {
            model = false;
            return;
        }
        if (model) {
            try {
                adminService.createModel(brandId, new CarParameterRequest(line));
            } catch (EntityAlreadyExistException | EntityNotFoundException ignored) {
            }
        } else {
            try {
                brandId = adminService.createBrand(new CarParameterRequest(line)).getId();
            } catch (EntityAlreadyExistException ignored) {
                brandId = brandRepository.findByName(line).getId();
            }
        }

    }
}