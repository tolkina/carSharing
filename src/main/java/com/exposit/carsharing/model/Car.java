package com.exposit.carsharing.model;
import javax.persistence.*;
/**
 * Created by Sergei on 10/12/2017.
 */

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Car() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
