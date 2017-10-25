package com.exposit.carsharing.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Model> models;
}
