package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "taskManager", cascade = CascadeType.ALL)
    @Setter
    List<TaskDirectory> taskDirectories;


    public List<Task> getTasks(LocalDate date)
    {
        List<Task> result = new ArrayList<>();
        for(TaskDirectory directory : taskDirectories)
        {
            if(directory.getTasks()!=null)
                result.addAll(directory.getTasks());
        }
        return result;
    }


    public void newTask(Task task)
    {
//        tasks.add(task);
    }
    public void newTaskDirectory()
    {

    }
}
