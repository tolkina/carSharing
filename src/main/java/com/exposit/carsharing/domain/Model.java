package com.exposit.carsharing.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "model")
public class Model extends AbstractCarParameterEntity {
    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Brand brand;
}
