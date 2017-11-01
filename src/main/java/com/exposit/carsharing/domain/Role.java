package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<Profile> profiles;

    public Role(String role) {
        this.role = role;
    }
}