package com.khaliuk.dao;

import com.khaliuk.model.Category;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category c", Category.class)
                .list();
    }

    @Override
    public Category getById(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Category c inner join fetch c.products where c.id = :id",
                        Category.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Category save(Category category) {
        sessionFactory.getCurrentSession().update(category);
        return category;
    }
}
