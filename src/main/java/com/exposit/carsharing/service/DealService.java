package com.exposit.carsharing.service;

import com.exposit.carsharing.dto.DealRequest;
import com.exposit.carsharing.dto.DealResponse;
import com.exposit.carsharing.exception.DealException;
import com.exposit.carsharing.exception.EntityNotFoundException;
import com.exposit.carsharing.exception.PrivilegeException;
import org.springframework.data.domain.Page;

public interface DealService {
    DealResponse get(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException;

    Page<DealResponse> getAllByCustomer(Long customerId, Integer page, Integer size, String sort, String direction)
            throws EntityNotFoundException;

    Page<DealResponse> getAllByOwner(Long ownerId, Integer page, Integer size, String sort, String direction) throws EntityNotFoundException;

    DealResponse startRental(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException, DealException;

    DealResponse stopRental(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException, DealException;

    DealResponse cancelBooking(Long dealId, Long principalId) throws EntityNotFoundException, PrivilegeException, DealException;

    DealResponse create(DealRequest dealRequest, Long customerId) throws EntityNotFoundException, DealException;
}
