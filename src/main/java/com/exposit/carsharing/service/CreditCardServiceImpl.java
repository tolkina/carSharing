package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.CreditCard;
import com.exposit.carsharing.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final ProfileService profileService;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, ProfileService profileService) {
        this.creditCardRepository = creditCardRepository;
        this.profileService = profileService;
    }

    @Override
    public CreditCard getCreditCard(Long id) {
        return creditCardRepository.findOne(id);
    }

    @Override
    public Collection<CreditCard> getAllCreditCards() {
        return creditCardRepository.findAll();
    }

    @Override
    public void createCreditCard(CreditCard creditCard, Long ownerId) throws EntityNotFoundException {
        creditCard.setOwner(profileService.getProfile(ownerId));
        creditCardRepository.save(creditCard);
    }
}
