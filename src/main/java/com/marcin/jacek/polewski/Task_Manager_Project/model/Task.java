package com.marcin.jacek.polewski.Task_Manager_Project.model;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.SubTaskNotFinishedException;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
public class Task {
    private int taskId;
    @Setter
    private String title;
    @Setter
    private String note;
    private LocalDateTime creationData;
    @Setter
    private LocalDateTime scheduledExecution;
    @Setter
    private LocalDateTime dueDate;
    private ArrayList<Task> subTasks;
    TaskState state;
    private ArrayList<LocalDateTime> reminders;

    Task(int taskId, String title, LocalDateTime scheduledExecution, LocalDateTime dueDate)
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
