package com.marcinpolewski.taskmanagerproject.model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

@Getter
public class TaskDirectory {
    /* represents a container for other Directories or Tasks*/
    @Setter
    private String name;
    @Setter
    private TaskDirectory parentDirectory;
    private ArrayList<TaskDirectory> subDirectories;
    private ArrayList<Task> tasks;

    TaskDirectory(TaskDirectory parentDirectory, String name)
    {
        this.parentDirectory = parentDirectory;
        this.name = name;
    }

    public void addTask(Task newTask)
    {
        tasks.add(newTask);
    }

    public TaskDirectory newSubDirectory(String name)
    {
        TaskDirectory newDir = new TaskDirectory(this, name);
        subDirectories.add(newDir);
        return newDir;
    }



}
