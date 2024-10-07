package com.marcin.jacek.polewski.Task_Manager_Project.Events;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import javafx.event.ActionEvent;
import lombok.Getter;

public class TaskDirectoryPressedEvent extends ActionEvent {
    @Getter
    private TaskDirectory taskDirectory;

    public TaskDirectoryPressedEvent(TaskDirectory taskDirectory)
    {
        this.taskDirectory = taskDirectory;
    }
}
