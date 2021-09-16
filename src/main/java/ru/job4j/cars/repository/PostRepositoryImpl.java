package ru.job4j.cars.repository;

import ru.job4j.cars.model.CarBodyType;
import ru.job4j.cars.model.CarBrand;
import ru.job4j.cars.model.Post;

import java.time.LocalDate;
import java.util.List;

import static ru.job4j.cars.util.HibernateUtil.inTx;
import static ru.job4j.cars.util.HibernateUtil.getFromTx;


public class PostRepositoryImpl implements PostRepository {

    private static final class SingletonHolder {
        private static final PostRepositoryImpl INSTANCE = new PostRepositoryImpl();
    }

    public static PostRepositoryImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }


    @Override
    public List<Post> getByCreatedDate(LocalDate date) {
        return getFromTx(session -> session
                .createQuery("from Post where created=:date", Post.class)
                .setParameter("date", date)
                .getResultList());

    }

    @Override
    public List<Post> getAll() {
        return getFromTx(session -> session
                .createQuery("from Post order by description", Post.class)
                .getResultList());
    }

    @Override
    public Post get(int id) {
        return getFromTx(session -> session
                .createQuery("from Post where id=:id", Post.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public CarBodyType getCarBodyType(int id) {
        return getFromTx(session -> session
                .createQuery("from CarBodyType where id=:id", CarBodyType.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public CarBrand getCarBrand(int id) {
        return getFromTx(session -> session
                .createQuery("from CarBrand where id=:id", CarBrand.class)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    public List<Post> getWithImages() {
        return getFromTx(session -> session
                .createQuery("from Post p where p.images is not empty", Post.class)
                .getResultList());
    }

    @Override
    public List<Post> getByBrand(CarBrand brand) {
        return getFromTx(session -> session
                .createQuery("from Post where carBrand=:brand", Post.class)
                .setParameter("brand", brand)
                .getResultList());
    }

    @Override
    public List<CarBrand> getAllCarBrands() {
        return getFromTx(session -> session
                .createQuery("from CarBrand order by name", CarBrand.class)
                .getResultList());
    }

    @Override
    public List<CarBodyType> getAllCarBodyTypes() {
        return getFromTx(session -> session
                .createQuery("from CarBodyType order by name", CarBodyType.class)
                .getResultList());
    }

    @Override
    public Post save(Post post) {
        inTx(session -> session.saveOrUpdate(post));
        return post;
    }
}
