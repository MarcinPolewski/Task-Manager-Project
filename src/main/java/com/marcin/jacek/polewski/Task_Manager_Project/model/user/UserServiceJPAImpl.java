package com.marcin.jacek.polewski.Task_Manager_Project.model.user;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service.TaskManagerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceJPAImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    UserServiceJPAImpl(UserDAO userDAO)
    {

        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public User add(User user) {

        user = userDAO.add(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }
}
