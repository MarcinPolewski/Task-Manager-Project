package com.marcin.jacek.polewski.Task_Manager_Project.model.task.DAO;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDAOJPA implements TaskDAO{

    private EntityManager entityManager;

    TaskDAOJPA(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void update(Task task) {
        entityManager.merge(task);
    }

    @Override
    @Transactional
    public void add(Task task) {

        task.setTaskId(entityManager.merge(task).getTaskId());
    }
}
