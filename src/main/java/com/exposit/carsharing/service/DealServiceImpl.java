package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.repository.DealRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ProfileService profileService;
    private final AdService adService;

    public DealServiceImpl(DealRepository DealRepository, ProfileService profileService, AdService adService) {
        this.dealRepository = DealRepository;
        this.profileService = profileService;
        this.adService = adService;
    }

    @Override
    public boolean isExist(Long id) {
        return dealRepository.findOne(id) != null;
    }

    @Override
    public Deal get(Long id) throws EntityNotFoundException {
        Deal deal = dealRepository.findOne(id);
        if (deal == null) {
            throw new EntityNotFoundException(String.format("Deal with id %d not found", id));
        }
        return deal;
    }

    @Override
    public List<Deal> getAll() {
        return dealRepository.findAll();
    }

    @Override
    public List<Deal> getAllByCustomer(Long customerId) throws EntityNotFoundException {
        return dealRepository.findAllByCustomer(profileService.getProfile(customerId));
    }

    @Override
    public List<Deal> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        return dealRepository.findAllByOwner(profileService.getProfile(ownerId));
    }

    @Override
    public void create(Deal deal, Long adId, Long ownerId, Long customerId) throws EntityNotFoundException {
        adService.getAd(adId);
        deal.setOwner(profileService.getProfile(ownerId));
        deal.setCustomer(profileService.getProfile(customerId));
        dealRepository.save(deal);
    }
}
