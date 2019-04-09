package com.khaliuk.dao;

import com.khaliuk.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getByUsername(String username) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User u inner join fetch u.roles where u.username =:username",
                        User.class)
                .setParameter("username", username)
                .uniqueResult();
    }

    @Override
    public User addUser(User user) {
        Long id = (Long) sessionFactory.getCurrentSession().save(user);
        user.setId(id);
        return user;
    }

    @Override
    public User getByToken(String token) {
        return sessionFactory.getCurrentSession()
                .createQuery("from User u where u.token = :token", User.class)
                .setParameter("token", token)
                .uniqueResult();
    }

    @Override
    public User updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }
}
