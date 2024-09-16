package com.marcinpolewski.taskmanagerproject.model;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Task {
    private int taskId;
    @Setter
    private String title;
    @Setter
    private String note;
    private ZonedDateTime creationData;
    @Setter
    private ZonedDateTime scheduledExecution;
    @Setter
    private ZonedDateTime dueDate;
    private ArrayList<Task> subTasks;
    TaskState state;
    private ArrayList<ZonedDateTime> reminders;

    Task(int taskId, String title, ZonedDateTime scheduledExecution, ZonedDateTime dueDate)
    {
        this.taskId = taskId;
        this.title = title;
        this.scheduledExecution =scheduledExecution;
        this.dueDate = dueDate;
    }

    void addSubTask(Task subTask)
    {
        subTasks.add(subTask);
    }

    void setState(TaskState newState)
    {
        if(newState = TaskState.DONE)
        {
            if(allSubTasksCompleted())
            {
                state = TaskState.DONE;
            }
            else
            {
                throw
            }
        }
        else
        {
            state = newState;
        }

    }
}
