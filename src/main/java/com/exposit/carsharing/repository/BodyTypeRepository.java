package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    BodyType findByName(String name);
}
