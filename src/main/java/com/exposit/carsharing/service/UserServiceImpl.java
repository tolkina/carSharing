package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.DriverLicense;
import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.domain.Role;
import com.exposit.carsharing.dto.UserRequest;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.repository.DriverLicenseRepository;
import com.exposit.carsharing.repository.PassportDataRepository;
import com.exposit.carsharing.repository.ProfileRepository;
import com.exposit.carsharing.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DriverLicenseRepository driverLicenseRepository;
    private final PassportDataRepository passportDataRepository;

    public UserServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper,
                           RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
                           DriverLicenseRepository driverLicenseRepository,
                           PassportDataRepository passportDataRepository) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.driverLicenseRepository = driverLicenseRepository;
        this.passportDataRepository = passportDataRepository;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws EntityAlreadyExistException {
        return createProfile(userRequest,
                Stream.of(roleRepository.findByRole("ROLE_USER")).collect(Collectors.toList()));
    }

    @Override
    public UserResponse createAdmin(UserRequest userRequest) throws EntityAlreadyExistException {
        return createProfile(userRequest, roleRepository.findAll());
    }

    private UserResponse createProfile(UserRequest userRequest, List<Role> roles) throws EntityAlreadyExistException {
        checkEmail(userRequest.getEmail());

        Profile profile = new Profile();
        profile.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        profile.setEmail(userRequest.getEmail());
        profile.setLogin(userRequest.getLogin());
        profile.setRoles(roles);
        profileRepository.save(profile);

        PassportData passportData = new PassportData();
        DriverLicense driverLicense = new DriverLicense();
        driverLicense.setOwner(profile);
        passportData.setOwner(profile);
        passportDataRepository.save(passportData);
        driverLicenseRepository.save(driverLicense);

        profile.setPassportData(passportData);
        profile.setDriverLicense(driverLicense);
        return modelMapper.map(profile, UserResponse.class);
    }

    private void checkEmail(String email) throws EntityAlreadyExistException {
        if (profileRepository.findByEmail(email) != null) {
            throw new EntityAlreadyExistException(String.format("Email %s already exist", email));
        }
    }
}


