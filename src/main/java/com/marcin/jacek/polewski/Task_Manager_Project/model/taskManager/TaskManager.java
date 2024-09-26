package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name="Task_Managers")
@Getter
@Setter
public class TaskManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_manager_id")
    private int taskManagerId;

    @Column(name="user_id")
    private int userId;

    @Transient
    private List<Task> tasks;


    public void newTask()
    {

    }
    public void newTaskDirectory()
    {

    }
}
