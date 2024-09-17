package com.marcin.jacek.polewski.Task_Manager_Project.model;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.SubTaskNotFinishedException;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.ArrayList;

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

    boolean allSubTasksCompleted(){
        return false;
    }

    void setState(TaskState newState) throws SubTaskNotFinishedException
    {
        if(newState == TaskState.DONE)
        {
            if(allSubTasksCompleted())
            {
                state = TaskState.DONE;
            }
            else
            {
                throw new SubTaskNotFinishedException();
            }
        }
        else
        {
            state = newState;
        }

    }
}
