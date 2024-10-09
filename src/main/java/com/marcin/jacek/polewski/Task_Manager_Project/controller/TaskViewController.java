package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class TaskViewController implements ControllerInterface{

    @Setter
    private Task task;

    @Override
    public void initializeScene() {

    }
}
