package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.dto.AdResponse;
import com.exposit.carsharing.dto.ProfileResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.AdRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Ad getAd(Long id) throws EntityNotFoundException {
        Ad ad = adRepository.findOne(id);
        if (ad == null) {
            throw new EntityNotFoundException("Ad", id);
        }
        return ad;
    }

    @Override
    public AdResponse getAdResponse(Long id) throws EntityNotFoundException {
        return modelMapper.map(getAd(id), AdResponse.class);
    }

    @Override
    public List<Ad> getAll() {
        return adRepository.findAll();
    }

    @Override
    public List<AdResponse> getAllAdByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        List<AdResponse> ads = new ArrayList<>();
        adRepository.findAllByOwner(owner).forEach(ad -> ads.add(modelMapper.map(ad,AdResponse.class)));
        return ads;
    }

    @Override
    public AdResponse createAd(AdRequest adRequest, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        Ad ad = modelMapper.map(adRequest, Ad.class);
        ad.setOwner(profileService.get(ownerId));
        ad.setCar(carService.getCar(carId));
        adRepository.save(ad);
        return modelMapper.map(ad, AdResponse.class);
    }

    @Override
    public void delete(Long adId) throws PrivilegeException, EntityNotFoundException {
        /*if (!getAd(adId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }*/
        adRepository.delete(adId);
    }
}
