package com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.DAO;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubTaskDAOJPA implements SubTaskDAO{
    private EntityManager entityManager;

    @Autowired
    SubTaskDAOJPA(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    @Override
    public List<SubTask> find(Task task) {
        TypedQuery<SubTask> query = entityManager.createQuery("FROM SubTask WHERE mainTaskId=:id", SubTask.class);
        query.setParameter("id", task.getTaskId());
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(ArrayList<SubTask> subTasks) {
        for(SubTask subTask: subTasks)
        {
            entityManager.merge(subTask);
        }
    }
}
