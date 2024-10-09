package com.marcin.jacek.polewski.Task_Manager_Project.model;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.DAO.TaskDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.UserService;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class TaskManagerApp {
    // has current user, it's task manager, notification handler
    private User currentUser;

    private TaskDirectoryService taskDirectoryService;
    private UserService userService;
    private TaskDAO taskDAO;

    @Autowired
    TaskManagerApp(UserService userService,
            TaskDirectoryService taskDirectoryService,
            TaskDAO taskDAO
    )
    {
        this.taskDirectoryService = taskDirectoryService;
        this.userService = userService;
        this.taskDAO = taskDAO;

    }

    private void loadTasks()
    {
        // @TODO move this responsibility elsewhere???
        TaskManager currentTaskManager = currentUser.getTaskManager();
        List<Task> tasks = taskDAO.find(currentTaskManager);

        taskDirectoryService.clear();
        taskDirectoryService.addTasks(tasks);


        currentTaskManager.setTasks(tasks);

    }

    public void setCurrentUser(User user)
    {
        if(currentUser!=null)
        {
            userService.update(currentUser);
        }
        currentUser = user;

        loadTasks();
    }

    public void logOutUser()
    {
        if(currentUser!=null)
        {
            userService.update(currentUser);
            currentUser = null;
            taskDirectoryService.clear();
        }

    }

    public void newTask(Task task)
    {
        // add to task manager
        currentUser.getTaskManager().newTask(task);
        // add to task directory service
        taskDirectoryService.addTask(task);
    }
}
