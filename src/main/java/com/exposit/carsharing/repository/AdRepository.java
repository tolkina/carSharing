package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Ad;
import com.exposit.carsharing.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {
    List<Ad> findAllByOwner(Profile owner);
}
