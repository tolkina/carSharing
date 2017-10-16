package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.model.CreditCard;

import java.util.Collection;
import java.util.List;

public interface CreditCardService {
    boolean isExist(Long creditCardId);

    CreditCard get(Long id) throws EntityNotFoundException;

    Collection<CreditCard> getAll();

    List<CreditCard> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    void create(CreditCard creditCard, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException;

    void delete(Long creditCarId, Long ownerId) throws PrivilegeException, EntityNotFoundException;
}
