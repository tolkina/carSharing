package com.exposit.carsharing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "deal")
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

    public Deal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBookingStartTime() {
        return bookingStartTime;
    }

    public void setBookingStartTime(Date bookingStartTime) {
        this.bookingStartTime = bookingStartTime;
    }

    public Date getRentalStartTime() {
        return rentalStartTime;
    }

    public void setRentalStartTime(Date rentalStartTime) {
        this.rentalStartTime = rentalStartTime;
    }

    public Date getEstimatedRentalEndTime() {
        return estimatedRentalEndTime;
    }

    public void setEstimatedRentalEndTime(Date estimatedRentalEndTime) {
        this.estimatedRentalEndTime = estimatedRentalEndTime;
    }

    public Date getRentalEndTime() {
        return rentalEndTime;
    }

    public void setRentalEndTime(Date rentalEndTime) {
        this.rentalEndTime = rentalEndTime;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Profile getOwner() {
        return owner;
    }

    public void setOwner(Profile owner) {
        this.owner = owner;
    }

    public Profile getCustomer() {
        return customer;
    }

    public void setCustomer(Profile customer) {
        this.customer = customer;
    }
}
