package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityAlreadyExistException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.domain.CreditCard;
import com.exposit.carsharing.domain.Profile;
import com.exposit.carsharing.repository.CreditCardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;
    private final ProfileService profileService;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository, ProfileService profileService) {
        this.creditCardRepository = creditCardRepository;
        this.profileService = profileService;
    }

    @Override
    public boolean isExist(Long creditCardId) {
        return creditCardRepository.findOne(creditCardId) != null;
    }

    @Override
    public CreditCard get(Long id) throws EntityNotFoundException {
        CreditCard creditCard = creditCardRepository.findOne(id);
        if (creditCard == null) {
            throw new EntityNotFoundException("Credit card", id);
        }
        return creditCard;
    }

    @Override
    public Collection<CreditCard> getAll() {
        return creditCardRepository.findAll();
    }

    @Override
    public List<CreditCard> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        Profile owner = profileService.get(ownerId);
        return creditCardRepository.findAllByOwner(owner);
    }

    @Override
    public void create(CreditCard creditCard, Long ownerId) throws EntityNotFoundException, EntityAlreadyExistException {
        if (creditCard.getId() != null && isExist(creditCard.getId())) {
            throw new EntityAlreadyExistException("Credit card", creditCard.getId());
        }
        creditCard.setOwner(profileService.get(ownerId));
        creditCardRepository.save(creditCard);
    }

    @Override
    public void delete(Long creditCarId, Long ownerId) throws PrivilegeException, EntityNotFoundException {
        if (!get(creditCarId).getOwner().getId().equals(ownerId)) {
            throw new PrivilegeException();
        }
        creditCardRepository.delete(creditCarId);
    }
}
