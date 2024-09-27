package com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.DAO;

import com.marcin.jacek.polewski.Task_Manager_Project.model.subTask.SubTask;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;

import java.util.ArrayList;
import java.util.List;

public interface SubTaskDAO {
    List<SubTask> find(Task task);
    void update(ArrayList<SubTask> subTasks);
}
