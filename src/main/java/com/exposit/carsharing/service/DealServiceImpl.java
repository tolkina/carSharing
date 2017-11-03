package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.dto.DealRequest;
import com.exposit.carsharing.dto.DealResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.DealRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ProfileService profileService;
    private final AdService adService;
    private final ModelMapper modelMapper;

    public DealServiceImpl(DealRepository DealRepository, ProfileService profileService, AdService adService, ModelMapper modelMapper) {
        this.dealRepository = DealRepository;
        this.profileService = profileService;
        this.adService = adService;
        this.modelMapper = modelMapper;
    }

    @Override
    public DealResponse get(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException {
        Deal deal = getDeal(dealId);
        checkPrivilege(deal, principalId);
        return mapToResponse(deal);
    }

    @Override
    public List<DealResponse> getAll() {
        return mapToListResponse(dealRepository.findAll());
    }

    @Override
    public List<DealResponse> getAllByCustomer(Long customerId) throws EntityNotFoundException {
        return mapToListResponse(dealRepository.findAllByCustomer(profileService.getProfile(customerId)));
    }

    @Override
    public List<DealResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException {
        return mapToListResponse(dealRepository.findAllByOwner(profileService.getProfile(ownerId)));
    }

    @Override
    public DealResponse create(DealRequest dealRequest, Long adId, Long ownerId, Long customerId)
            throws EntityNotFoundException {
        Deal deal = mapFromRequest(dealRequest);
        adService.getAd(adId);
        deal.setOwner(profileService.getProfile(ownerId));
        deal.setCustomer(profileService.getProfile(customerId));
        dealRepository.save(deal);
        return mapToResponse(deal);
    }

    private Deal mapFromRequest(DealRequest dealRequest) {
        return modelMapper.map(dealRequest, Deal.class);
    }

    private DealResponse mapToResponse(Deal deal) {
        return modelMapper.map(deal, DealResponse.class);
    }

    private List<DealResponse> mapToListResponse(List<Deal> deals) {
        List<DealResponse> dealResponses = new ArrayList<>();
        deals.forEach(deal -> dealResponses.add(mapToResponse(deal)));
        return dealResponses;
    }

    private void checkPrivilege(Deal deal, Long principalId) throws PrivilegeException, EntityNotFoundException {
        if (!deal.getCustomer().getId().equals(principalId) || !deal.getOwner().getId().equals(principalId)) {
            throw new PrivilegeException();
        }
    }

    private Deal getDeal(Long dealId) throws EntityNotFoundException {
        Deal deal = dealRepository.findOne(dealId);
        if (deal == null) {
            throw new EntityNotFoundException("Deal", dealId);
        }
        return deal;
    }
}
