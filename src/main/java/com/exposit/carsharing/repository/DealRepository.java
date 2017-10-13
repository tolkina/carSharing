package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.Deal;
import com.exposit.carsharing.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealRepository extends JpaRepository<Deal, Long> {
    List<Deal> findAllByOwner(Profile owner);

    List<Deal> findAllByCustomer(Profile customer);
}
