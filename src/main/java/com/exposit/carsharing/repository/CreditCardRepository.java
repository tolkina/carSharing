package com.exposit.carsharing.repository;

import com.exposit.carsharing.model.CreditCard;
import com.exposit.carsharing.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByFirstName(String firstName);

    List<CreditCard> findAllByOwner(Profile owner);
}
