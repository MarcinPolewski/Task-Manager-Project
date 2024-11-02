package com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Task_Directories")
public class TaskDirectory implements TaskDirectoryItem{
    /* represents a container for other Directories or Tasks*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Setter
    private int id;

    @Column(name="name")
    @Setter
    private String name;

    @Setter
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER
    )
    @JoinColumn(name = "parent_id")
    private TaskDirectory parentDirectory;

    @OneToMany(mappedBy = "parentDirectory", cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    private List<TaskDirectory> subDirectories;

    @OneToMany(mappedBy = "enclosingFolder", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name="task_manager_id")
    @Setter
    TaskManager taskManager;

    public TaskDirectory(String name, TaskManager taskManager)
    {
        this.name = name;
        this.taskManager = taskManager;
        this.parentDirectory = null;
    }

    public void addTask(Task newTask)
    {
        if(tasks==null)
            tasks = new ArrayList<>();

        tasks.add(newTask);
    }

    public TaskDirectory newSubDirectory(String name)
    {
        if(subDirectories==null)
            subDirectories = new ArrayList<>();

        TaskDirectory newDir = new TaskDirectory(name, taskManager);
        subDirectories.add(newDir);
        newDir.setParentDirectory(this);

        taskManager.getTaskDirectories().add(newDir);

        return newDir;
    }

    public TaskDirectory findChildDirectory(String str)
    {
        if(subDirectories==null)
            return null;
        for(TaskDirectory subDir : subDirectories)
        {
            if(subDir.getName().equals(str))
            {
                return subDir;
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return name;
    }
}
