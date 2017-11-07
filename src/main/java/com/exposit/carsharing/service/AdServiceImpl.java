package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.*;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.AdRepository;
import com.exposit.carsharing.repository.CarRepository;
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
    private final CarRepository carRepository;

    public AdServiceImpl(ProfileService profileService, AdRepository adRepository, CarService carService, ModelMapper modelMapper, CarRepository carRepository) {
        this.profileService = profileService;
        this.adRepository = adRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
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
        adRepository.findAllByOwner(owner).forEach(ad -> ads.add(modelMapper.map(ad, AdResponse.class)));
        return ads;
    }

    @Override
    public AdResponse createAd(AdRequest adRequest, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException {
        Ad ad = modelMapper.map(adRequest, Ad.class);
        Car car = carService.getCar(carId);

        ad.setOwner(profileService.get(ownerId));
        ad.setCar(carService.getCar(carId));
        adRepository.save(ad);
        car.setAd(ad);
        carRepository.save(car);
        return modelMapper.map(ad, AdResponse.class);
    }

    @Override
    public AdResponse updateAd(Long adId, AdRequest adRequest) throws EntityNotFoundException {
        Ad ad = getAd(adId);
        ad.setCarLocation(adRequest.getCarLocation());
        ad.setReturnPlace(adRequest.getReturnPlace());
        ad.setCostPerHour(adRequest.getCostPerHour());
        ad.setCostPerDay(adRequest.getCostPerDay());
        ad.setCostPer3Days(adRequest.getCostPer3Days());
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
