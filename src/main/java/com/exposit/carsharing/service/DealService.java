package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.model.Deal;

import java.util.List;

public interface DealService {
    boolean isExist(Long id);

    Deal getDeal(Long id) throws EntityNotFoundException;

    List<Deal> getAllDeals();

    List<Deal> getAllMyDeals(Long customerId) throws EntityNotFoundException;

    List<Deal> getAllDealsWithMe(Long ownerId) throws EntityNotFoundException;

    void createDeal(Deal deal, Long ownerId, Long customerId) throws EntityNotFoundException;
}
