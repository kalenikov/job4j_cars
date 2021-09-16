package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository {
    List<Post> getByCreatedDate(LocalDate date);

    List<Post> getWithImages();

    List<Post> getAll();

    Post save(Post post);

    Post get(int id);

    List<Post> getByBrand(CarBrand brand);

    CarBrand getCarBrand(int id);

    List<CarBrand> getAllCarBrands();

    CarBodyType getCarBodyType(int id);

    List<CarBodyType> getAllCarBodyTypes();
}
