package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.Ad;

import java.util.List;

public interface AdService {
    boolean isExist(Long id);

    Ad get(Long id) throws EntityNotFoundException;

    List<Ad> getAll();

    List<Ad> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    void create(Ad ad, Long ownerId, Long carId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long adId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
