package com.marcin.jacek.polewski.Task_Manager_Project.Events;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import javafx.event.ActionEvent;
import lombok.Getter;

public class TaskPressedEvent extends ActionEvent{
    @Getter
    private Task task;

    public TaskPressedEvent(Task task)
    {
        this.task = task;
    }

}
