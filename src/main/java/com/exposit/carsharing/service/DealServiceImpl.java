package com.exposit.carsharing.service;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.AdStatus;
import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.domain.DealStatus;
import com.exposit.carsharing.dto.DealRequest;
import com.exposit.carsharing.dto.DealResponse;
import com.exposit.carsharing.exception.DealException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import com.exposit.carsharing.repository.DealRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DealServiceImpl implements DealService {
    private final DealRepository dealRepository;
    private final ProfileService profileService;
    private final AdService adService;
    private final ModelMapper modelMapper;
    private final CreditCardService creditCardService;

    public DealServiceImpl(DealRepository DealRepository, ProfileService profileService, AdService adService,
                           ModelMapper modelMapper, CreditCardService creditCardService) {
        this.dealRepository = DealRepository;
        this.profileService = profileService;
        this.adService = adService;
        this.modelMapper = modelMapper;
        this.creditCardService = creditCardService;
    }

    @Override
    public DealResponse get(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException {
        Deal deal = getDeal(dealId);
        checkPrivilege(deal, principalId);
        return mapToResponse(deal);
    }

    @Override
    public Page<DealResponse> getAllByCustomer(Long customerId, Integer page, Integer size, String sort,
                                               String direction) throws EntityNotFoundException {
        Pageable pageRequest = getPageRequest(page, size, sort, direction);
        Page<Deal> dealPage = dealRepository.findAllByCustomer(profileService.getProfile(customerId), pageRequest);
        return new PageImpl<>(mapToListResponse(dealPage.getContent()), pageRequest, dealPage.getTotalElements());
    }

    @Override
    public Page<DealResponse> getAllByOwner(Long ownerId, Integer page, Integer size, String sort,
                                            String direction) throws EntityNotFoundException {
        Pageable pageRequest = getPageRequest(page, size, sort, direction);
        Page<Deal> dealPage = dealRepository.findAllByOwner(profileService.getProfile(ownerId), pageRequest);
        return new PageImpl<>(mapToListResponse(dealPage.getContent()), pageRequest, dealPage.getTotalElements());
    }

    @Override
    public DealResponse startRental(Long dealId, Long principalId)
            throws EntityNotFoundException, PrivilegeException, DealException {
        Deal deal = getDeal(dealId);
        checkOwner(deal, principalId);
        checkStatus(deal, DealStatus.BOOKING);
        deal.setRentalStartTime(LocalDateTime.now());
        deal.setEstimatedRentalEndTime(calculateEstimatedRentalTime(deal));
        deal.setStatus(DealStatus.RENTAL_START);
        return mapToResponse(deal);
    }

    @Override
    public DealResponse stopRental(Long dealId, Long principalId)
            throws EntityNotFoundException, PrivilegeException, DealException {
        Deal deal = getDeal(dealId);
        checkOwner(deal, principalId);
        checkStatus(deal, DealStatus.RENTAL_START);
        deal.setRentalEndTime(LocalDateTime.now());
        deal.setPrice(recountPrice(deal));
        deal.setStatus(DealStatus.RENTAL_END);
        deal.getAd().setStatus(AdStatus.ACTUAL);
        return mapToResponse(deal);
    }

    @Override
    public DealResponse cancelBooking(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException, DealException {
        Deal deal = getDeal(dealId);
        checkCustomer(deal, principalId);
        checkStatus(deal, DealStatus.BOOKING);
        deal.setStatus(DealStatus.CANCEL_BOOKING);
        deal.getAd().setStatus(AdStatus.ACTUAL);
        return mapToResponse(deal);
    }

    @Override
    public DealResponse create(DealRequest dealRequest, Long customerId) throws EntityNotFoundException, DealException {
        Ad ad = adService.getAd(dealRequest.getAdId());
        if (!ad.getStatus().equals(AdStatus.ACTUAL)) {
            throw new EntityNotFoundException("Ad", ad.getId());
        }
        if (ad.getOwner().getId().equals(customerId)) {
            throw new DealException("This is your ad.");
        }
        Deal deal = new Deal();
        deal.setOwner(ad.getOwner());
        deal.setCustomer(profileService.getProfile(customerId));
        deal.setCreditCard(creditCardService.getCreditCard(dealRequest.getCreditCardId(), customerId));
        deal.setBookingStartTime(LocalDateTime.now());
        deal.setPrice(multiple(dealRequest.getDaysForRent(), ad.getCostPerDay()));
        deal.setAd(ad);
        deal.setDaysForRent(dealRequest.getDaysForRent());
        dealRepository.save(deal);
        ad.setStatus(AdStatus.TAKEN);
        return mapToResponse(deal);
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

    private BigDecimal multiple(Long itemQuantity, BigDecimal itemPrice) {
        return itemPrice.multiply(new BigDecimal(itemQuantity));
    }

    private void checkOwner(Deal deal, Long principalId) throws PrivilegeException {
        if (!deal.getOwner().getId().equals(principalId)) {
            throw new PrivilegeException();
        }
    }

    private void checkCustomer(Deal deal, Long principalId) throws PrivilegeException {
        if (!deal.getCustomer().getId().equals(principalId)) {
            throw new PrivilegeException();
        }
    }

    private void checkStatus(Deal deal, DealStatus dealStatus) throws DealException {
        if (!deal.getStatus().equals(dealStatus)) {
            throw new DealException(String.format("Can't perform the action. The deal has status %s", deal.getStatus()));
        }
    }

    private LocalDateTime calculateEstimatedRentalTime(Deal deal) throws DealException {
        return deal.getRentalStartTime().plusDays(deal.getDaysForRent());
    }

    private BigDecimal recountPrice(Deal deal) {
        BigDecimal newPrice = deal.getPrice();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(
                Duration.between(deal.getEstimatedRentalEndTime(), deal.getRentalEndTime()).toMillis());
        if (minutes > 15) {
            long days = (long) Math.ceil(minutes / 60.0 / 24.0);
            BigDecimal fine = multiple(days, deal.getAd().getCostPerDay());
            newPrice = newPrice.add(fine);
        }
        return newPrice;
    }

    private Pageable getPageRequest(Integer page, Integer size, String sort, String direction) {
        if (direction.toLowerCase().equals("desc")) {
            return new PageRequest(page - 1, size, Sort.Direction.DESC, sort);
        }
        return new PageRequest(page - 1, size, Sort.Direction.ASC, sort);
    }
}
