package ru.job4j.cars.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "posts")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;
    private String description;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean sold;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_id")
    private CarBodyType carBodyType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Set<Image> images;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @CreationTimestamp
    private LocalDate created;

    public Post(String description, CarBrand carBrand, CarBodyType carBodyType, User author, Set<Image> images) {
        this.description = description;
        this.carBrand = carBrand;
        this.carBodyType = carBodyType;
        this.author = author;
        this.images = images;
    }
}
