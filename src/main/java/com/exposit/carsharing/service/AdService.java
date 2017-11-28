package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.dto.AdRequest;
import com.exposit.carsharing.dto.AdResponse;
import com.exposit.carsharing.exception.AdException;
import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdService {
    Ad getAd(Long id) throws EntityNotFoundException;

    AdResponse getAdResponse(Long id) throws EntityNotFoundException;

    List<AdResponse> getAll();

    Page<AdResponse> getAllNotMyActual(Long principalId, Integer page, Integer size, String sort, String direction)
            throws EntityNotFoundException;

    Page<AdResponse> getAllByOwner(Long ownerId, Integer page, Integer size, String sort, String direction)
            throws EntityNotFoundException;

    AdResponse create(AdRequest adRequest, Long ownerId, Long carId) throws EntityNotFoundException,
            EntityAlreadyExistException, PrivilegeException;

    AdResponse update(Long ownerId, Long adId, AdRequest adRequest) throws EntityNotFoundException, PrivilegeException,
            AdException;

    void delete(Long ownerId, Long adId) throws PrivilegeException, EntityNotFoundException, AdException;

    AdResponse setActual(Long ownerId, Long adId) throws EntityNotFoundException, PrivilegeException, AdException;

    AdResponse setNotActual(Long ownerId, Long adId) throws EntityNotFoundException, PrivilegeException, AdException;
}
