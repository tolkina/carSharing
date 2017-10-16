package com.exposit.carsharing.repositoryAdmin;

import com.exposit.carsharing.modelAdmin.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
