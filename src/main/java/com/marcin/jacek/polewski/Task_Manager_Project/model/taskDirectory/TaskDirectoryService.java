package com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskDirectoryService {
    @Getter
    private TaskDirectory root = new TaskDirectory();

    public void addTask(Task task)
    {
        String strPath = task.getEnclosingFolderPath();
        Path path = Paths.get(strPath);

        TaskDirectory currentFolder = root;
        for(Path pathElement: path)
        {
            TaskDirectory result = currentFolder.findChildDirectory(pathElement.toString());
            if(result == null)
            {
                currentFolder = currentFolder.newSubDirectory(pathElement.toString());
            }
            else
            {
                currentFolder = result;
            }
        }

        // add task to folder
        currentFolder.addTask(task);
    }

    public void addTasks(List<Task> tasks)
    {
        if(tasks!=null)
        {
            for (Task task : tasks) {
                this.addTask(task);
            }
        }
    }

    public void clear()
    {
        root = new TaskDirectory();
    }


}
