package ru.job4j.cars.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "images")
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String path;

    public Image(String path) {
        this.path = path;
    }
}
