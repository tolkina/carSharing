package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brand")
public class Brand extends AbstractCarParameterEntity {
    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Model> models;
}
