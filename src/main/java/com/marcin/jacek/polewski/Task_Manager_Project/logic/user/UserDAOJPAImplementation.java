package com.marcin.jacek.polewski.Task_Manager_Project.model.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOJPAImplementation implements UserDAO{

    private EntityManager entityManager;

    @Autowired
    UserDAOJPAImplementation(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String givenUsername) {
        TypedQuery<User> query = entityManager.createQuery("FROM User " +
                "WHERE username=:givenUsername", User.class);
        query.setParameter("givenUsername", givenUsername);
        return query.getSingleResult();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User add(User user) {
        // setting id=0, to make sure that new user is created
        // and wrong input will not override other user in db
        user.setId(0);
        user = entityManager.merge(user);
        return user;
    }

    @Override
    public void delete(User user) {
        User syncedUser = entityManager.find(User.class, user.getId());
        if(syncedUser!=null)
            entityManager.remove(syncedUser);
    }
}
