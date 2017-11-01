package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.PassportData;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDataRepository extends JpaRepository<PassportData, Long> {
    PassportData findByOwner(Profile owner);
}
