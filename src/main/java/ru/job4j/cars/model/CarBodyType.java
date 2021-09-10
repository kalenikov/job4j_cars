package ru.job4j.cars.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_body_types")
@NoArgsConstructor
public class CarBodyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public CarBodyType(String name) {
        this.name = name;
    }
}
