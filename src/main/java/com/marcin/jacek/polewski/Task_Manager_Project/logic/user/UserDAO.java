package com.marcin.jacek.polewski.Task_Manager_Project.model.user;

import java.util.List;

public interface UserDAO {
    public List<User> findAll();
    public User findById(int id);
    public User findByUsername(String username);

    public void update(User user);
    public User add(User user);
    public void delete(User user);
}
