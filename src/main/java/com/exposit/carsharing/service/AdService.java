package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.dto.AdResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.Ad;

import java.util.List;

public interface AdService {
    boolean isExist(Long id);

    Ad getAd(Long id) throws EntityNotFoundException;

    AdResponse getAdResponse(Long id) throws EntityNotFoundException;

    List<Ad> getAll();

    List<AdResponse> getAllAdByOwner(Long ownerId) throws EntityNotFoundException;

    AdResponse createAd(AdRequest adRequest, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long adId) throws PrivilegeException, EntityNotFoundException;
}
