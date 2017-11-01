package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.UserRequest;
import com.exposit.carsharing.dto.UserResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.repository.ProfileRepository;
import com.exposit.carsharing.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final ModelMapper modelMapper;
    private final ProfileRepository profileRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ProfileRepository profileRepository, ModelMapper modelMapper,
                           RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.profileRepository = profileRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws EntityAlreadyExistException {
        Profile profile = createProfile(userRequest);
        profile.setRoles(Stream.of(roleRepository.findByRole("ROLE_USER")).collect(Collectors.toSet()));
        profileRepository.save(profile);
        return modelMapper.map(profile, UserResponse.class);
    }

    @Override
    public UserResponse createAdmin(UserRequest userRequest) throws EntityAlreadyExistException {
        Profile profile = createProfile(userRequest);
        profile.setRoles(roleRepository.findAll());
        profileRepository.save(profile);
        return modelMapper.map(profile, UserResponse.class);
    }

    private Profile createProfile(UserRequest userRequest) throws EntityAlreadyExistException {
        if (profileRepository.findByEmail(userRequest.getEmail()) != null) {
            throw new EntityAlreadyExistException(String.format("Email %s already used", userRequest.getEmail()));
        }
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        return modelMapper.map(userRequest, Profile.class);
    }
}
