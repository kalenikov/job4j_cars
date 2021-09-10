package ru.job4j.cars.model;

import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "posts")
@ToString
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;

    @Column(columnDefinition = "boolean default false", nullable = false)
    private boolean sold;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private CarBrand carBrand;

    @ManyToOne
    @JoinColumn(name = "body_id")
    private CarBodyTape carBodyTape;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private Set<Image> images;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @CreationTimestamp
    private LocalDate created;
}
