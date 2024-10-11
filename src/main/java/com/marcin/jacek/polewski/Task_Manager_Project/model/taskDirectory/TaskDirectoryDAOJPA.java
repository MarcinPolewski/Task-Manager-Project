package com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDirectoryDAOJPA implements TaskDirectoryDAO{
    private EntityManager entityManager;

    @Autowired
    TaskDirectoryDAOJPA(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public List<TaskDirectory> find(TaskManager taskManager)
    {
         TypedQuery<TaskDirectory> query = entityManager.createQuery
                 ("FROM TaskDirectory td WHERE td.taskManager.taskManagerId = :id", TaskDirectory.class);
        query.setParameter("id", taskManager.getTaskManagerId());

        return query.getResultList();
    }

}