package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.util.List;

import static ru.job4j.cars.HibernateUtil.doInTransaction;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public List<Post> getByCreatedDate(LocalDate date) {
        return doInTransaction(session -> session
                .createQuery("from Post where created=:date", Post.class)
                .setParameter("date", date)
                .getResultList());
    }

    @Override
    public List<Post> getWithImages() {
        return doInTransaction(session -> session
                .createQuery("from Post p where p.images is not empty", Post.class)
                .getResultList());
    }

    @Override
    public List<Post> getByBrand(CarBrand brand) {
        return doInTransaction(session -> session
                .createQuery("from Post where carBrand=:brand", Post.class)
                .setParameter("brand", brand)
                .getResultList());
    }

    public static void main(String[] args) {
        PostRepository repo = new PostRepositoryImpl();
        System.out.println(repo.getByCreatedDate(LocalDate.now()));
        System.out.println(repo.getWithImages());
    }
}
