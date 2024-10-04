package com.marcin.jacek.polewski.Task_Manager_Project.model.user;

import com.marcin.jacek.polewski.Task_Manager_Project.model.taskManager.TaskManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Users")
@NoArgsConstructor
@Getter
@Setter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @OneToOne(mappedBy="taskManager", cascade = CascadeType.ALL)
    private TaskManager taskManager;

    public User(String username)
    {
        this.username = username;
    }
}
