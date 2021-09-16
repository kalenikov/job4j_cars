package ru.job4j.cars.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_brands")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"id"})
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public CarBrand(String name) {
        this.name = name;
    }
}
