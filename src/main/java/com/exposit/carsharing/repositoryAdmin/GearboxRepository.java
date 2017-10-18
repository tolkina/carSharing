package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.Gearbox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GearboxRepository extends JpaRepository<Gearbox, Long> {
    Gearbox findByName(String name);
}
