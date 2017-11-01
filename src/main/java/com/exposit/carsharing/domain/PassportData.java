package com.exposit.carsharing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "passport_data")
@Data
public class PassportData implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String series;

    private Integer number;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "place_of_issue")
    private String placeOfIssue;

    @Column(name = "valid_until")
    private LocalDate validUntil;

    @Column(name = "registration_photo")
    private String registrationPhotoUrl;

    @Column(name = "photo")
    private String photoUrl;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;
}
