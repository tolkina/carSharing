package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role extends AbstractEntity {
    @Column(nullable = false, length = 45)
    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<Profile> profiles;

    public Role(String role) {
        this.role = role;
    }
}