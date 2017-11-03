package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "deal")
public class Deal extends AbstractEntity {
    @Column(name = "booking_start_time")
    private Date bookingStartTime;

    @Column(name = "rental_start_time")
    private Date rentalStartTime;

    @Column(name = "estimated_rental_end_time")
    private Date estimatedRentalEndTime;

    @Column(name = "rental_end_time")
    private Date rentalEndTime;

    private BigDecimal deposit;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Profile customer;
}
