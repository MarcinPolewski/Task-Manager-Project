package com.marcin.jacek.polewski.Task_Manager_Project.model.task;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.SubTaskNotFinishedException;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int taskId;

    @Column(name="task_manager_id")
    private int taskManagerId;

    @Column(name="title")
    private String title;

    @Column(name="notes")
    private String note;

    @Column(name="creation_date")
    private LocalDateTime creationData;

    @Column(name="scheduled_execution")
    private LocalDateTime scheduledExecution;

    @Column(name="due_date")
    private LocalDateTime dueDate;

    @Column(name="state")
    TaskState state;

    @Transient
    private ArrayList<SubTask> subTasks;

    @Transient
    private ArrayList<LocalDateTime> reminders;

    Task(int taskId, String title, LocalDateTime scheduledExecution, LocalDateTime dueDate)
    {
        this.taskId = taskId;
        this.title = title;
        this.scheduledExecution =scheduledExecution;
        this.dueDate = dueDate;
    }

    void addSubTask(SubTask subTask)
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
