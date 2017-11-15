package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.CreditCard;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.dto.CreditCardRequest;
import com.exposit.carsharing.dto.CreditCardResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.repository.CreditCardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final ProfileService profileService;
    private final ModelMapper modelMapper;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository,
                                 ProfileService profileService,
                                 ModelMapper modelMapper) {
        this.creditCardRepository = creditCardRepository;
        this.profileService = profileService;
        this.modelMapper = modelMapper;
    }

    @Override
    public CreditCardResponse get(Long creditCarId, Long ownerId) throws EntityNotFoundException {
        return mapToResponse(getCreditCard(creditCarId, ownerId));
    }

    @Override
    public List<CreditCardResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.getProfile(ownerId);
        List<CreditCardResponse> creditCards = new ArrayList<>();
        creditCardRepository.findAllByOwner(owner).forEach(creditCard -> creditCards.add(mapToResponse(creditCard)));
        return creditCards;
    }

    @Override
    public CreditCardResponse create(CreditCardRequest creditCardRequest, Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.getProfile(ownerId);
        CreditCard creditCard = mapFromRequest(creditCardRequest);
        creditCard.setOwner(owner);
        creditCardRepository.save(creditCard);
        return mapToResponse(creditCard);
    }

    @Override
    public void delete(Long creditCarId, Long ownerId) throws EntityNotFoundException {
        creditCardRepository.delete(getCreditCard(creditCarId, ownerId));
    }

    @Override
    public CreditCard getCreditCard(Long creditCardId, Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.getProfile(ownerId);
        CreditCard creditCard = creditCardRepository.findByIdAndOwner(creditCardId, owner);
        if (creditCard == null) {
            throw new EntityNotFoundException(String.format(
                    "Profile with id %d don't have credit credit card with id %d", ownerId, creditCardId));
        }
        return creditCard;
    }

    private CreditCard mapFromRequest(CreditCardRequest creditCardRequest) {
        return modelMapper.map(creditCardRequest, CreditCard.class);
    }

    private CreditCardResponse mapToResponse(CreditCard creditCard) {
        return modelMapper.map(creditCard, CreditCardResponse.class);
    }
}
