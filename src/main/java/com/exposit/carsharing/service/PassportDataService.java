package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.PassportData;

import java.util.Collection;

public interface PassportDataService {
    PassportData getPassportData(Long id);

    Collection<PassportData> getAllPassportData();

    void createPassportData(PassportData passportData, Long ownerId) throws EntityNotFoundException;
}
