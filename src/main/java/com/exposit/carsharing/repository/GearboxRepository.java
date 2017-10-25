package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Gearbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearboxRepository extends JpaRepository<Gearbox, Long> {
    Gearbox findByName(String name);
}
