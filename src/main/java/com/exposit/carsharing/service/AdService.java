package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Ad;

import java.util.List;

public interface AdService {
    boolean isExist(Long id);

    Ad getAd(Long id) throws EntityNotFoundException;

    List<Ad> getAllAds();

    void createAd(Ad ad, Long ownerId, Long carId) throws EntityNotFoundException;
}
