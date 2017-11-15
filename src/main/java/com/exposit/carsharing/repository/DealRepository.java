package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Deal;
import com.exposit.carsharing.domain.DealStatus;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findAllByOwner(Profile owner);

    List<Deal> findAllByCustomer(Profile customer);

    List<Deal> findAllByStatus(DealStatus status);
}
