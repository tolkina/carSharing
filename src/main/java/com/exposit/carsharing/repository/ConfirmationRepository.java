package com.exposit.carsharing.repository;

import com.exposit.carsharing.domain.Confirmation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {
}
