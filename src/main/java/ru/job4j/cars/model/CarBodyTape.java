package ru.job4j.cars.model;

import javax.persistence.*;

@Entity
@Table(name = "car_body_types")
public class CarBodyTape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
}
