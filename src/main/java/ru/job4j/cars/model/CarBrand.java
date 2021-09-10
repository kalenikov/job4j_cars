package ru.job4j.cars.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_brands")
@NoArgsConstructor
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public CarBrand(String name) {
        this.name = name;
    }
}
