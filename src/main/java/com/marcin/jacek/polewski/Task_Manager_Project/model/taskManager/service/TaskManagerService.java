package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.service;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;

public interface TaskManagerService {
    TaskManager getTaskManager(User user);
}
