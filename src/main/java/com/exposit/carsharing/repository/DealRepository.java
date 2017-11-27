package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.domain.DealStatus;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    Page<Deal> findAllByOwner(Profile owner, Pageable pageable);

    Page<Deal> findAllByCustomer(Profile customer, Pageable pageable);

    List<Deal> findAllByStatus(DealStatus status);
}
