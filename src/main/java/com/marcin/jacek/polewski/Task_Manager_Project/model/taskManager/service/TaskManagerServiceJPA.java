package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.DAO.SubTaskDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.DAO.TaskDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskManagerServiceJPA implements TaskManagerService {

    private TaskManagerDAO taskManagerDAO;
    private TaskDAO taskDAO;
    private SubTaskDAO subTaskDAO;
    private TaskDirectoryService taskDirectoryService;

    @Autowired
    TaskManagerServiceJPA(TaskManagerDAO taskManagerDAO,
                          SubTaskDAO subTaskDAO,
                          TaskDAO taskDAO,
                          TaskDirectoryService taskDirectoryService)
    {
        this.taskManagerDAO = taskManagerDAO;
        this.taskDAO = taskDAO;
        this.subTaskDAO = subTaskDAO;
        this.taskDirectoryService = taskDirectoryService;
    }

    @Override
    public TaskManager getTaskManager(User user) {
        // get task manager id assigned to this use
        TaskManager taskManager = taskManagerDAO.find(user);

        // get tasks assigned to this task manager
        List<Task> tasks = taskDAO.find(taskManager);
        taskManager.setTasks(tasks);


        for(Task task: tasks)
        {
            // get subtasks assigned to all
            List<SubTask> subTasks = subTaskDAO.find(task);
            task.setSubTasks(new ArrayList<>(subTasks));

            // add this Task to TaskDirectoryService
            taskDirectoryService.addTask(task);
        }

        return taskManager;
    }

    @Override
    @Transactional
    public void update(TaskManager taskManager) {
        // update tasks
        taskDAO.update(taskManager.getTasks());
        // update sub tasks
        for(Task task : taskManager.getTasks())
        {
            if(!task.getSubTasks().isEmpty())
                subTaskDAO.update(task.getSubTasks());
        }
    }
}
