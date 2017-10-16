package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.BodyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BodyTypeRepository extends JpaRepository<BodyType, Long> {
    BodyType findByBodyType(String bodyType);
}
