package com.marcin.jacek.polewski.Task_Manager_Project.model;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service.TaskManagerService;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TaskManagerApp {
    // has current user, it's task manager, notification handler
    private User currentUser;

    private TaskManagerService taskManagerService;
    private TaskDirectoryService taskDirectoryService;

    @Autowired
    TaskManagerApp(TaskManagerService taskManagerService,
                   TaskDirectoryService taskDirectoryService)
    {
        this.taskManagerService = taskManagerService;
        this.taskDirectoryService = taskDirectoryService;
    }

    private void switchTaskManager(User newUser)
    {
        // sync database with previous user
        if(currentUser!=null)
        {
            taskManagerService.update(currentUser.getTaskManager());
        }
        currentUser = newUser;
        TaskManager tm = taskManagerService.getTaskManager(newUser);
               currentUser.setTaskManager(tm);
    }

    public void setCurrentUser(User user)
    {
        switchTaskManager(user);
        this.currentUser = user;
    }

}
