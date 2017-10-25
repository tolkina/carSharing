package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.AdRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {
    private final ProfileService profileService;
    private final AdRepository adRepository;
    private final CarService carService;
    private final ModelMapper modelMapper;

    public AdServiceImpl(ProfileService profileService, AdRepository adRepository, CarService carService, ModelMapper modelMapper) {
        this.profileService = profileService;
        this.adRepository = adRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isExist(Long id) {
        return adRepository.findOne(id) != null;
    }

    @Override
    public Ad get(Long id) throws EntityNotFoundException {
        Ad ad = adRepository.findOne(id);
        if (ad == null) {
            throw new EntityNotFoundException("Ad", id);
        }
        return ad;
    }

    @Override
    public List<Ad> getAll() {
        return adRepository.findAll();
    }

    @Override
    public List<Ad> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        return adRepository.findAllByOwner(owner);
    }

    @Override
    public void create(Ad ad, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (ad.getId() != null && isExist(ad.getId())) {
            throw new EntityAlreadyExistException("Ad", ad.getId());
        }
        ad.setOwner(profileService.get(ownerId));
        ad.setCar(modelMapper.map(carService.get(carId), Car.class));
        adRepository.save(ad);
    }

    @Override
    public void delete(Long adId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(adId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        adRepository.delete(adId);
    }
}
