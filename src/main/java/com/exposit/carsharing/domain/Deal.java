package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deal")
public class Deal extends AbstractEntity {
    @Column(name = "booking_start_time")
    private LocalDate bookingStartTime;

    @Column(name = "rental_start_time")
    private LocalDate rentalStartTime;

    @Column(name = "estimated_rental_end_time")
    private LocalDate estimatedRentalEndTime;

    @Column(name = "rental_end_time")
    private LocalDate rentalEndTime;

    private BigDecimal deposit;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Profile customer;
}
