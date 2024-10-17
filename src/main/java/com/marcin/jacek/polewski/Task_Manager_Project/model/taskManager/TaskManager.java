package com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.taskDirectory.TaskDirectory;
import com.marcin.jacek.polewski.Task_Manager_Project.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.collection.spi.PersistentBag;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Array;
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
    List<TaskDirectory> taskDirectories;

    public void setTaskDirectories(List<TaskDirectory> taskDirectories)
    {
        this.taskDirectories = taskDirectories;

        // @TODO do it better? during loading from db new instance of tm is created, therefore
        // it causes problem down the line (in loaded TM directories are not loaded)
        for (TaskDirectory td : taskDirectories)
            td.setTaskManager(this);
    }

    public List<Task> getTasks(LocalDate date)
    {
        List<Task> result = new ArrayList<>();
        for(TaskDirectory directory : taskDirectories)
        {
            if(directory.getTasks()!=null)
            {
                for(Task task : directory.getTasks())
                {
                    if(task.getScheduledExecution().toLocalDate().equals(date))
                    {
                        result.add(task);
                    }
                }
            }
        }
        return result;
    }


    public void newTask(Task task)
    {
//        tasks.add(task);
    }
    public void newTaskDirectory(TaskDirectory taskDirectory)
    {
        // nawet jak dany tm ma foldery wchodzi do tego ifa
//        if(taskDirectories instanceof PersistentBag<?> && !((PersistentBag<?>) taskDirectories).wasInitialized())
//            this.taskDirectories = new ArrayList<>();

        // @TODO fix this line
        // wywala bo jest lazy type
        this.taskDirectories.add(taskDirectory);
    }
}
