package com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;


public interface TaskDirectoryDAO {
    public List<TaskDirectory> find(TaskManager taskManager);
}
