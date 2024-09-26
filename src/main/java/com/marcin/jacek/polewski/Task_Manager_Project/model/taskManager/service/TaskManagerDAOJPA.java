package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class TaskManagerDAOJPA implements TaskManagerDAO{

    private EntityManager entityManager;

    TaskManagerDAOJPA(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public TaskManager find(User user) {
        return entityManager.find(TaskManager.class, user.getId());
    }
}
