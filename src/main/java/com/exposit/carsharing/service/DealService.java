package com.exposit.carsharing.service;

import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.domain.Deal;

import java.util.List;

public interface DealService {
    boolean isExist(Long id);

    Deal get(Long id) throws EntityNotFoundException;

    List<Deal> getAll();

    List<Deal> getAllByCustomer(Long customerId) throws EntityNotFoundException;

    List<Deal> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    void create(Deal deal, Long adId, Long ownerId, Long customerId) throws EntityNotFoundException;
}
