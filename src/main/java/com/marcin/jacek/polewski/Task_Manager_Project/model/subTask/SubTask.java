package com.marcin.jacek.polewski.Task_Manager_Project.model.subTask;

import com.marcin.jacek.polewski.Task_Manager_Project.model.task.Task;
import com.marcin.jacek.polewski.Task_Manager_Project.model.task.TaskState;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Sub_Tasks")
@Getter
@NoArgsConstructor
@Setter
public class SubTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    }, fetch = FetchType.EAGER)
    @JoinColumn(name="main_task_id")
    Task mainTask;

    @Column(name="title")
    private String title;

    @Column(name="state")
    private TaskState state;

}
