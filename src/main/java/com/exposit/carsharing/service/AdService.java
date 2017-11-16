package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.dto.AdResponse;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.Collection;
import java.util.List;

public interface AdService {
    Ad getAd(Long id) throws EntityNotFoundException;

    AdResponse getAdResponse(Long id) throws EntityNotFoundException;

    List<AdResponse> getAll();

    List<AdResponse> getAllNotMyActual(Long principalId) throws EntityNotFoundException;

    Collection<AdResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    AdResponse create(AdRequest adRequest, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException, PrivilegeException;

    AdResponse update(Long ownerId, Long adId, AdRequest adRequest) throws EntityNotFoundException, PrivilegeException;

    void delete(Long ownerId, Long adId) throws PrivilegeException, EntityNotFoundException;
}
