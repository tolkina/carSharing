package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.AdStatus;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    Page<Ad> findAllByOwnerIsNotAndStatus(Profile owner, AdStatus status, Pageable pageable);

    Page<Ad> findAllByOwner(Profile owner, Pageable pageable);
}
