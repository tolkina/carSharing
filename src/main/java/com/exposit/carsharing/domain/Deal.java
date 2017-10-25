package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deal")
@Data
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_start_time")
    private Date bookingStartTime;

    @Column(name = "rental_start_time")
    private Date rentalStartTime;

    @Column(name = "estimated_rental_end_time")
    private Date estimatedRentalEndTime;

    @Column(name = "rental_end_time")
    private Date rentalEndTime;

    private double deposit;

    private double price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Profile customer;
}
