package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Ad;
import com.exposit.carsharing.repository.AdRepository;
import com.exposit.carsharing.repository.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {
    private final ProfileService profileService;
    private final AdRepository adRepository;

    public AdServiceImpl(ProfileService profileService, AdRepository adRepository) {
        this.profileService = profileService;
        this.adRepository = adRepository;
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
    public void createAd(Ad ad, Long ownerId) throws EntityNotFoundException {
        ad.setOwner(profileService.getProfile(ownerId));
        adRepository.save(ad);
    }
}
