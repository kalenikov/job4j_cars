package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.util.List;

import static ru.job4j.cars.HibernateUtil.doInTransaction;
import static ru.job4j.cars.HibernateUtil.doInTransactionWithReturn;

public class PostRepositoryImpl implements PostRepository {
    @Override
    public List<Post> getByCreatedDate(LocalDate date) {
        return doInTransactionWithReturn(session -> session
                .createQuery("from Post where created=:date", Post.class)
                .setParameter("date", date)
                .getResultList());
    }

    @Override
    public List<Post> getWithImages() {
        return doInTransactionWithReturn(session -> session
                .createQuery("from Post p where p.images is not empty", Post.class)
                .getResultList());
    }

    @Override
    public List<Post> getByBrand(CarBrand brand) {
        return doInTransactionWithReturn(session -> session
                .createQuery("from Post where carBrand=:brand", Post.class)
                .setParameter("brand", brand)
                .getResultList());
    }

    @Override
    public Post save(Post post) {
        doInTransaction(session -> session.saveOrUpdate(post));
        return post;
    }
}
