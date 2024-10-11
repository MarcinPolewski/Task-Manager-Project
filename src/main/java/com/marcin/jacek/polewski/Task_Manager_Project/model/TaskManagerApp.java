package com.marcin.jacek.polewski.Task_Manager_Project.model;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryDAO;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
public class TaskManagerApp {
    // has current user, it's task manager, notification handler
    private User currentUser;

    private UserService userService;
    private TaskDirectoryDAO taskDirectoryDAO;

    @Autowired
    TaskManagerApp(UserService userService,
            TaskDirectoryDAO taskDirectoryDAO)
    {
        this.userService = userService;
        this.taskDirectoryDAO = taskDirectoryDAO;

    }

    private void loadTasks()
    {
        TaskManager currentTaskManager = currentUser.getTaskManager();
        List<TaskDirectory> directories = taskDirectoryDAO.find(currentTaskManager);

        currentTaskManager.setTaskDirectories(directories);
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
        }

    }

    public void newTask(Task task)
    {
        // add to task manager
        currentUser.getTaskManager().newTask(task);
        // add to task directory service
    }
}
