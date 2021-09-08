package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Column(columnDefinition = "false", nullable = false)
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "body_id")
    private CarBodyTape carBodyTape;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
