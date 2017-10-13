package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Ad;
import com.exposit.carsharing.repository.AdRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final ProfileService profileService;
    private final AdRepository adRepository;
    private final CarService carService;

    public AdServiceImpl(ProfileService profileService, AdRepository adRepository, CarService carService) {
        this.profileService = profileService;
        this.adRepository = adRepository;
        this.carService = carService;
    }

    @Override
    public boolean isExist(Long id) {
        return adRepository.findOne(id) != null;
    }

    @Override
    public Ad getAd(Long id) throws EntityNotFoundException {
        Ad ad = adRepository.findOne(id);
        if (ad == null) {
            throw new EntityNotFoundException(String.format("Ad with id %d not found", id));
        }
        return ad;
    }

    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public void createAd(Ad ad, Long ownerId, Long carId) throws EntityNotFoundException {
        ad.setOwner(profileService.getProfile(ownerId));
        ad.setCar(carService.getCar(carId));
        adRepository.save(ad);
    }
}
