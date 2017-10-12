package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.CreditCard;

import java.util.Collection;

public interface CreditCardService {

    CreditCard getCreditCard(Long id);

    Collection<CreditCard> getAllCreditCards();

    void createCreditCard(CreditCard creditCard, Long ownerId) throws EntityNotFoundException;
}
