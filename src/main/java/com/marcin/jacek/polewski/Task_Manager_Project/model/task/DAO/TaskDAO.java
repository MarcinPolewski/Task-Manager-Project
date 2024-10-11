package com.marcin.jacek.polewski.Task_Manager_Project.model.task.DAO;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;

import java.util.List;

public interface TaskDAO {
    //List<Task> find(TaskManager taskManager);
    void update(List<Task> tasks);
}
