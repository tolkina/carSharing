package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard extends AbstractEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private Integer number;

    @Column(name = "valid_until")
    private LocalDate validUntil;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;

    @OneToMany(mappedBy = "creditCard", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Deal> deals;
}
