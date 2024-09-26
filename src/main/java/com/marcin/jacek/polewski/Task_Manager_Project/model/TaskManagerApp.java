package com.marcin.jacek.polewski.Task_Manager_Project.model;

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

    TaskManagerApp(TaskManagerService taskManagerService)
    {
        this.taskManagerService = taskManagerService;
    }

    private void switchTaskManager(User newUser)
    {
        TaskManager tm = taskManagerService.getTaskManager(newUser);
        // sync database with previous user
        // load new task manager from db
    }

    public void setCurrentUser(User user)
    {
        switchTaskManager(user);
        this.currentUser = user;
    }

}
