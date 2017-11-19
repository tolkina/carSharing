package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "passport_data")
public class PassportData extends AbstractEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    private String series;

    private String number;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Profile owner;
}
