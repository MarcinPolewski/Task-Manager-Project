package com.marcin.jacek.polewski.Task_Manager_Project.model.task;

import com.marcin.jacek.polewski.Task_Manager_Project.exceptions.SubTaskNotFinishedException;
import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectoryItem;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Tasks")
public class Task implements TaskDirectoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int taskId;

    @Column(name="title")
    private String title;

    @Column(name="notes")
    @Setter
    private String note;

    @Column(name="creation_date")
    private LocalDateTime creationData;

    @Column(name="scheduled_execution")
    private LocalDateTime scheduledExecution;

    @Column(name="due_date")
    private LocalDateTime dueDate;

    @Column(name="state")
    private TaskState state;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinColumn(name="enclosing_folder_id")
    private TaskDirectory enclosingFolder;

    @OneToMany(mappedBy = "mainTask", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<SubTask> subTasks;

    public Task(int taskId, String title, LocalDateTime scheduledExecution, LocalDateTime dueDate, TaskDirectory parentFolder)
    {
        this.taskId = taskId;
        this.title = title;
        this.scheduledExecution =scheduledExecution;
        this.dueDate = dueDate;
        this.state = TaskState.CREATED;
        this.enclosingFolder = parentFolder;

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

    @Override
    public String toString()
    {
        return title;
    }
}
