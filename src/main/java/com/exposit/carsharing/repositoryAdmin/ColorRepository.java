package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByName(String name);
}
