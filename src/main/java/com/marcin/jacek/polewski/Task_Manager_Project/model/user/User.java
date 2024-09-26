package com.marcin.jacek.polewski.Task_Manager_Project.model.user;

import com.marcin.jacek.polewski.Task_Manager_Project.model.TaskManager;
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

    @Transient
    private TaskManager taskManager;

    User(int id, String username)
    {
        this.id = id;
        this.username = username;
    }
}
