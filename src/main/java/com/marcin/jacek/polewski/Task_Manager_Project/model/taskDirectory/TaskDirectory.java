package com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@NoArgsConstructor
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
        if(tasks==null)
            tasks = new ArrayList<>();

        tasks.add(newTask);
    }

    public TaskDirectory newSubDirectory(String name)
    {
        if(subDirectories==null)
            subDirectories = new ArrayList<>();

        TaskDirectory newDir = new TaskDirectory(this, name);
        subDirectories.add(newDir);
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
