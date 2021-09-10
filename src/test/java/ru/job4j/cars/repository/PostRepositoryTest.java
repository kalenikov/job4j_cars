package ru.job4j.cars.repository;

import org.junit.Test;
import ru.job4j.cars.model.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PostRepositoryTest {

    private static final PostRepository repo = new PostRepositoryImpl();

    @Test
    public void getWithImages() {
        repo.save(new Post(
                "post1",
                new CarBrand("brand1"),
                new CarBodyType("type1"),
                new User("user1"),
                Set.of(
                        new Image("path1"),
                        new Image("path2")))
        );
        assertEquals(repo.getWithImages().get(0).getDescription(), "post1");
    }

    @Test
    public void getByBrand() {
        CarBrand brand1 = new CarBrand("brand1");
        repo.save(new Post(
                "post2",
                brand1,
                new CarBodyType("type1"),
                new User("user1"),
                Collections.emptySet())
        );
        assertEquals(repo.getByBrand(brand1).get(0).getDescription(), "post2");
    }

    @Test
    public void getByCreatedDate() {
        repo.save(new Post(
                "post3",
                new CarBrand("brand1"),
                new CarBodyType("type1"),
                new User("user1"),
                Collections.emptySet())
        );
        assertEquals(repo.getByCreatedDate(LocalDate.now()).get(0).getDescription(), "post3");
    }
}