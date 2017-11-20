package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.AdStatus;
import com.exposit.carsharing.domain.Car;
import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.dto.AdResponse;
import com.exposit.carsharing.exception.AdException;
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

    public AdServiceImpl(ProfileService profileService, AdRepository adRepository, CarService carService,
                         ModelMapper modelMapper) {
        this.profileService = profileService;
        this.adRepository = adRepository;
        this.carService = carService;
        this.modelMapper = modelMapper;
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
        return mapToResponse(getAd(id));
    }

    @Override
    public List<AdResponse> getAll() {
        return mapAllToResponse(adRepository.findAll());
    }

    @Override
    public List<AdResponse> getAllNotMyActual(Long principalId) throws EntityNotFoundException {
        return mapAllToResponse(adRepository.findAllByOwnerIsNotAndStatus(
                profileService.getProfile(principalId), AdStatus.ACTUAL));
    }

    @Override
    public List<AdResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        return mapAllToResponse(profileService.getProfile(ownerId).getAds());
    }

    @Override
    public AdResponse create(AdRequest adRequest, Long ownerId, Long carId)
            throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException {
        Car car = carService.getCar(carId);
        checkCarOwner(ownerId, car);
        checkAdExistForCar(car);
        Ad ad = mapFromRequest(adRequest);
        ad.setOwner(profileService.getProfile(ownerId));
        ad.setCar(carService.getCar(carId));
        adRepository.save(ad);
        return mapToResponse(ad);
    }

    @Override
    public AdResponse update(Long ownerId, Long adId, AdRequest adRequest)
            throws EntityNotFoundException, PrivilegeException, AdException {
        Ad ad = getAd(adId);
        checkAdOwner(ownerId, ad);
        checkTaken(ad);
        ad.setCarLocation(adRequest.getCarLocation());
        ad.setReturnPlace(adRequest.getReturnPlace());
        ad.setCostPerDay(adRequest.getCostPerDay());
        return mapToResponse(ad);
    }

    @Override
    public void delete(Long ownerId, Long adId) throws PrivilegeException, EntityNotFoundException, AdException {
        Ad ad = getAd(adId);
        checkAdOwner(ownerId, ad);
        checkTaken(ad);
        adRepository.delete(adId);
    }

    @Override
    public AdResponse setActual(Long ownerId, Long adId)
            throws EntityNotFoundException, PrivilegeException, AdException {
        Ad ad = getAd(adId);
        checkAdOwner(ownerId, ad);
        checkAdStatus(ad, AdStatus.NOT_RELEVANT);
        ad.setStatus(AdStatus.ACTUAL);
        return mapToResponse(ad);
    }

    @Override
    public AdResponse setNotActual(Long ownerId, Long adId)
            throws EntityNotFoundException, PrivilegeException, AdException {
        Ad ad = getAd(adId);
        checkAdOwner(ownerId, ad);
        checkAdStatus(ad, AdStatus.ACTUAL);
        ad.setStatus(AdStatus.NOT_RELEVANT);
        return mapToResponse(ad);
    }

    private void checkCarOwner(Long ownerId, Car car) throws PrivilegeException {
        if (!car.getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
    }

    private void checkAdOwner(Long ownerId, Ad ad) throws PrivilegeException {
        if (!ad.getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
    }

    private void checkAdExistForCar(Car car) throws EntityAlreadyExistException {
        if (car.getAd() != null) {
            throw new EntityAlreadyExistException(String.format("Ad for car with id %d already exist", car.getId()));
        }
    }

    private Ad mapFromRequest(AdRequest adRequest) {
        return modelMapper.map(adRequest, Ad.class);
    }

    private AdResponse mapToResponse(Ad ad) {
        return modelMapper.map(ad, AdResponse.class);
    }

    private List<AdResponse> mapAllToResponse(List<Ad> ads) {
        List<AdResponse> adResponses = new ArrayList<>();
        ads.forEach(ad -> adResponses.add(mapToResponse(ad)));
        return adResponses;
    }

    private void checkAdStatus(Ad ad, AdStatus status) throws AdException {
        if (!ad.getStatus().equals(status)) {
            throw new AdException(String.format("Can't perform the action. The ad has status %s", ad.getStatus()));
        }
    }

    private void checkTaken(Ad ad) throws AdException {
        if (ad.getStatus().equals(AdStatus.TAKEN)) {
            throw new AdException(String.format("Can't perform the action. The ad has status %s", ad.getStatus()));
        }
    }
}
