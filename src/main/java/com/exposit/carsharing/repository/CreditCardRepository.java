package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByFirstName(String firstName);
}
