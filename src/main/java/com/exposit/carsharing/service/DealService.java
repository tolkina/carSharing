package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.DealRequest;
import com.exposit.carsharing.dto.DealResponse;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;

import java.util.List;

public interface DealService {
    DealResponse get(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException;

    List<DealResponse> getAll();

    List<DealResponse> getAllByCustomer(Long customerId) throws EntityNotFoundException;

    List<DealResponse> getAllByOwner(Long ownerId) throws EntityNotFoundException;

    DealResponse create(DealRequest dealRequest, Long adId, Long ownerId, Long customerId) throws EntityNotFoundException;
}
