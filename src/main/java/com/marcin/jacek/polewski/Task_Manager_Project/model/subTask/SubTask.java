package com.marcin.jacek.polewski.Task_Manager_Project.model.subTask;

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

    @Column(name="main_task_id")
    private int mainTaskId;

    @Column(name="title")
    private String title;

    @Column(name="state")
    private int state;

}
