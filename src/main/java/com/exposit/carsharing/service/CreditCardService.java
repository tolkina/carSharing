package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.CreditCardRequest;
import com.exposit.carsharing.dto.CreditCardResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;

import java.util.List;

public interface CreditCardService {
    CreditCardResponse get(Long creditCarId, Long ownerId) throws EntityNotFoundException;

    List<CreditCardResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    CreditCardResponse create(CreditCardRequest creditCardRequest, Long ownerId) throws EntityNotFoundException;

    void delete(Long creditCarId, Long ownerId) throws EntityNotFoundException;
}
