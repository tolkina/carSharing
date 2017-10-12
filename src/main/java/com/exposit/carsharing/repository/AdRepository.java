package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
}
