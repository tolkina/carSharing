package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Deal;
import com.exposit.carsharing.repository.DealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ProfileService profileService;

    public DealServiceImpl(DealRepository DealRepository, ProfileService profileService) {
        this.dealRepository = DealRepository;
        this.profileService = profileService;
    }

    @Override
    public boolean isExist(Long id) {
        return dealRepository.findOne(id) != null;
    }

    @Override
    public Deal getDeal(Long id) throws EntityNotFoundException {
        Deal deal = dealRepository.findOne(id);
        if (deal == null) {
            throw new EntityNotFoundException(String.format("Deal with id %d not found", id));
        }
        return deal;
    }

    @Override
    public List<Deal> getAllDeals() {
        return dealRepository.findAll();
    }

    @Override
    public List<Deal> getAllMyDeals(Long customerId) throws EntityNotFoundException {
        return dealRepository.findAllByCustomer(profileService.getProfile(customerId));
    }

    @Override
    public List<Deal> getAllDealsWithMe(Long ownerId) throws EntityNotFoundException {
        return dealRepository.findAllByOwner(profileService.getProfile(ownerId));
    }

    @Override
    public void createDeal(Deal deal, Long ownerId, Long customerId) throws EntityNotFoundException {
        deal.setOwner(profileService.getProfile(ownerId));
        deal.setCustomer(profileService.getProfile(customerId));
        dealRepository.save(deal);
    }
}
