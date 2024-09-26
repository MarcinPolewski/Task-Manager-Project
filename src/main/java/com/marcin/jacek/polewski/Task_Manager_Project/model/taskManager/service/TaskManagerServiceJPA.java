package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.DAO.SubTaskDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.DAO.TaskDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskManagerServiceJPA implements TaskManagerService {

    private TaskManagerDAO taskManagerDAO;
    private TaskDAO taskDAO;
    private SubTaskDAO subTaskDAO;

    @Autowired
    TaskManagerServiceJPA(TaskManagerDAO taskManagerDAO,
                          SubTaskDAO subTaskDAO,
                          TaskDAO taskDAO)
    {
        this.taskManagerDAO = taskManagerDAO;
        this.taskDAO = taskDAO;
        this.subTaskDAO = subTaskDAO;
    }

    @Override
    public TaskManager getTaskManager(User user) {
        // get task manager id assigned to this use
        TaskManager taskManager = taskManagerDAO.find(user);

        // get tasks assigned to this task manager
        List<Task> tasks = taskDAO.find(taskManager);
        taskManager.setTasks(tasks);

        // get subtasks assigned to all
        for(Task task: tasks)
        {
            List<SubTask> subTasks = subTaskDAO.find(task);
            task.setSubTasks(new ArrayList<>(subTasks));
        }

        return taskManager;
    }
}
