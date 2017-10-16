package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.PassportData;

import java.util.Collection;

public interface PassportDataService {
    boolean isExist(Long passportDataId);

    PassportData get(Long id) throws EntityNotFoundException;

    Collection<PassportData> getAll();

    void create(PassportData passportData, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long passportDataId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
