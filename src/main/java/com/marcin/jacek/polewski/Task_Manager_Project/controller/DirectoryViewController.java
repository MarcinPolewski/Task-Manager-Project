package com.marcin.jacek.polewski.Task_Manager_Project.controller;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.springframework.stereotype.Component;

@Component
public class DirectoryViewController implements ControllerInterface{

    @Setter
    private TaskDirectory taskDirectory;

}
