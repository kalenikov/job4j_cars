package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

import java.util.Optional;

import static ru.job4j.cars.util.HibernateUtil.inTx;
import static ru.job4j.cars.util.HibernateUtil.getFromTx;

public class UserRepositoryImpl implements UserRepository {

    private static final class SingletonHolder {
        private static final UserRepository INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepository getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public User save(User user) {
        inTx(session -> session.saveOrUpdate(user));
        return user;
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return getFromTx(
                session -> session
                        .createQuery("from User where email=:email", User.class)
                        .setParameter("email", email)
                        .uniqueResultOptional());
    }
}