package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "deal")
public class Deal extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DealStatus status;

    @Column(name = "booking_start_time")
    private Long bookingStartTime;

    @Column(name = "rental_start_time")
    private Long rentalStartTime;

    @Column(name = "estimated_rental_end_time")
    private Long estimatedRentalEndTime;

    @Column(name = "rental_end_time")
    private Long rentalEndTime;

    @Column(name = "hours_for_rent")
    private Long hoursForRent;

    private BigDecimal deposit;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Profile customer;

    @ManyToOne
    @JoinColumn(name = "credit_card_id", nullable = false)
    private CreditCard creditCard;

    @ManyToOne
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    public Deal() {
        this.status = DealStatus.BOOKING;
    }
}
