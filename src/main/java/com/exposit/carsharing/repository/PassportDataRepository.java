package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.PassportData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportDataRepository extends JpaRepository<PassportData, Long> {
}
